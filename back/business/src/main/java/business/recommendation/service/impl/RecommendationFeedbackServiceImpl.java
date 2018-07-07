package business.recommendation.service.impl;

import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.RecmdWeighEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.entity.workerentity.WorkerInfoEntity;
import business.recommendation.helper.Recommendation_MissionDbHelper;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import business.recommendation.service.RecommendationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RecommendationFeedbackServiceImpl implements RecommendationFeedbackService {

    @Autowired
    private Recommendation_WorkerDbHelper workerDbHelper;

    @Autowired
    private Recommendation_MissionDbHelper missionDbHelper;

    /*
    * 根据event反馈，调整推荐算法的权重，由于权重是百分比，因此需要重新计算
    * 通过workerId和missionId找到推荐算法编号。然后更改该编号，重新计算所有推荐算法的权重
    * */

    enum eventEnum{LIKE,UNLIKE,COMMON}

    @Override
    @Async("taskExecutor")
    @Transactional
    public void adjust(Long workerId, Long missionId, int event) {


        WorkerEntity workerEntity = workerDbHelper.findById(workerId);
        RecmdWeighEntity recmdWeighEntity = workerEntity.getRecmdWeighEntity();

        //基于相同主题的任务
        List<RecmdMissionEntity> label_recmdMissionEntityList = workerEntity.getLabel_recmdMissionEntityList();
        //基于用户兴趣相似性，推荐相似用户做过的任务
        List<RecmdMissionEntity> userInterest_recmdMissionEntityList = workerEntity.getUserInterest_recmdMissionEntityList();
        //基于用户能力相似性，推荐相似用户做过的任务
        List<RecmdMissionEntity> userTrait_recmdMissionEntityList = workerEntity.getUserTrait_recmdMissionEntityList();
        //用户不敢兴趣列表
        List<Long> uninterestList = workerEntity.getUninterestedList();

        //任务标签
        Set<Integer> labelSet = missionDbHelper.findById(missionId).getLabel();
        Integer[] labelArray = labelSet.toArray(new Integer[labelSet.size()]);

        //相同主题
        for (int i = 0; i < label_recmdMissionEntityList.size(); i++){
            if (label_recmdMissionEntityList.get(i).getMissionId() == missionId){
                double interestRefer  = 3;
                if (event == eventEnum.LIKE.ordinal()){
                    interestRefer = 5;
                }else if(event == eventEnum.UNLIKE.ordinal()){
                    interestRefer = -10;
                    uninterestList.add(missionId);
                }
                //增加兴趣指数
                Map<Integer,WorkerInfoEntity> workerInfoEntityMap = workerEntity.getWorkerInfo();
                for (int j = 0; j < labelSet.size(); j++){
                    workerInfoEntityMap.get(labelArray[j]).setInterestRefer((int)interestRefer + workerInfoEntityMap.get(labelArray[j]).getInterestRefer());
                    workerInfoEntityMap.get(labelArray[j]).setLastInterestTime(new Date());
                }
                workerEntity.setWorkerInfo(workerInfoEntityMap);
                recmdWeighEntity.setLabelRecmd(recmdWeighEntity.getLabelRecmd() + interestRefer/40);

                RecmdWeighEntity recmdWeighEntity1 = reCalculate(recmdWeighEntity);
                workerEntity.setRecmdWeighEntity(recmdWeighEntity1);
                workerDbHelper.update(workerEntity);
                return;
            }
        }

        //相似兴趣用户推荐
        //只需要更新该推荐指数即可
        for (int i = 0; i < userInterest_recmdMissionEntityList.size(); i++){
            if (userInterest_recmdMissionEntityList.get(i).getMissionId() == missionId){
                double interestRefer = 0.03;
                if (event == eventEnum.LIKE.ordinal()){
                    interestRefer = 0.05;
                }else if(event == eventEnum.UNLIKE.ordinal()){
                    interestRefer = -0.1;
                    uninterestList.add(missionId);
                }
                recmdWeighEntity.setInterestRecmd(recmdWeighEntity.getInterestRecmd() + interestRefer);

                RecmdWeighEntity recmdWeighEntity1 = reCalculate(recmdWeighEntity);
                workerEntity.setRecmdWeighEntity(recmdWeighEntity1);
                workerDbHelper.update(workerEntity);
                return;
            }
        }

        //相似能力用户推荐，同上
        for (int i = 0; i < userTrait_recmdMissionEntityList.size(); i++){
            if (userTrait_recmdMissionEntityList.get(i).getMissionId() == missionId){
                double interestRefer = 0.03;
                if (event == eventEnum.LIKE.ordinal()){
                    interestRefer = 0.05;
                }else if(event == eventEnum.UNLIKE.ordinal()){
                    interestRefer = -0.1;
                    uninterestList.add(missionId);
                }
                recmdWeighEntity.setTraitRecmd(recmdWeighEntity.getTraitRecmd() + interestRefer);

                RecmdWeighEntity recmdWeighEntity1 = reCalculate(recmdWeighEntity);
                workerEntity.setRecmdWeighEntity(recmdWeighEntity1);
                workerDbHelper.update(workerEntity);
                return;
            }
        }

        //普通的任务反馈，只更改兴趣指数
        double interestRefer  = 3;
        if (event == eventEnum.LIKE.ordinal()){
            interestRefer = 5;
        }else if(event == eventEnum.UNLIKE.ordinal()){
            interestRefer = -10;
            uninterestList.add(missionId);
        }
        //增加兴趣指数
        Map<Integer,WorkerInfoEntity> workerInfoEntityMap = workerEntity.getWorkerInfo();
        for (int j = 0; j < labelSet.size(); j++){
            workerInfoEntityMap.get(labelArray[j]).setInterestRefer((int)interestRefer + workerInfoEntityMap.get(labelArray[j]).getInterestRefer());
            workerInfoEntityMap.get(labelArray[j]).setLastInterestTime(new Date());
        }
        workerEntity.setWorkerInfo(workerInfoEntityMap);
        workerDbHelper.update(workerEntity);

    }

    public RecmdWeighEntity reCalculate(RecmdWeighEntity recmdWeighEntity){
        double total = 0.0;
        total += recmdWeighEntity.getColdRecmd();
        total += recmdWeighEntity.getHotRecmd();
        total += recmdWeighEntity.getInterestRecmd();
        total += recmdWeighEntity.getLabelRecmd();
        total += recmdWeighEntity.getRelationRecmd();
        total += recmdWeighEntity.getTraitRecmd();

        recmdWeighEntity.setLabelRecmd(recmdWeighEntity.getLabelRecmd() / total);
        recmdWeighEntity.setColdRecmd(recmdWeighEntity.getColdRecmd() / total);
        recmdWeighEntity.setLabelRecmd(recmdWeighEntity.getLabelRecmd() / total);
        recmdWeighEntity.setInterestRecmd(recmdWeighEntity.getInterestRecmd() / total);
        recmdWeighEntity.setRelationRecmd(recmdWeighEntity.getRelationRecmd() / total);
        recmdWeighEntity.setTraitRecmd(recmdWeighEntity.getTraitRecmd() / total);

        return recmdWeighEntity;
    }
}
