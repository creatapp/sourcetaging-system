package business.recommendation.service.impl;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.RecmdWeighEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.api.*;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import business.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private PopularRecommenderApi popularRecommenderApi;

    @Autowired
    private LabelRecommenderApi labelRecommenderApi;

    @Autowired
    private TailRecommenderApi tailRecommenderApi;

    @Autowired
    private UserInterestRecommenderApi userInterestRecommenderApi;

    @Autowired
    private UserTraitRecommenderApi userTraitRecommenderApi;

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Override
    @Transactional
    public ArrayList<RecmdMissionEntity> recommendPopular(Long workerId) {

        //Integer a=10;
        //Long workerId=a.longValue();

        ArrayList<RecmdMissionEntity> recmdMissionEntities=popularRecommenderApi.recomend(workerId);

        //过滤已做过的
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        Integer doneNum=workerEntity.getDoneMissionNum();
        DoneMissionEntity[] doneMissionEntities_worked=workerEntity.getDoneMissionEntities();
        Integer doingNum=workerEntity.getDoingMissionsNum();
        DoingMissionEntity[] doingMissionEntities_worked=workerEntity.getDoingMissionEntities();
        ArrayList<Long> mission_worked=new ArrayList<>();
        for(int i=0;i<doingNum;i++){
            mission_worked.add(doingMissionEntities_worked[i].getMissionId());
        }
        for(int i=0;i<doneNum;i++){
            mission_worked.add(doneMissionEntities_worked[i].getMissionId());
        }
        for(Long workedMissionId: mission_worked){
            for(int m=0;m<recmdMissionEntities.size();m++){
                if(recmdMissionEntities.get(m).getMissionId().equals(workedMissionId)){
                    recmdMissionEntities.remove(m);
                }
            }
        }

        ArrayList<RecmdMissionEntity> recmdMissionEntities_result=new ArrayList<>();
        for(int i=0;i<4&&i<recmdMissionEntities.size();i++){
            recmdMissionEntities_result.add(recmdMissionEntities.get(i));
        }
        return recmdMissionEntities_result;
    }

    @Override
    @Transactional
    public ArrayList<RecmdMissionEntity> recommendPersonal(Long workerId) {

        /*,每个推荐都出10个，再由本类选择
        //调用热门推荐
        //调用冷门推荐，目的是多样性和覆盖率
        //调用个性推荐，包括：
            1、基于用户能力相似性，推荐相似用户做过的任务
            2、基于用户兴趣相似性，推荐相似用户做过的任务
            3、基于相同主题的任务
            4、基于关联项挖掘，从用户做过的任务中挖掘用户的喜好
            5、其它算法
        */

        Map<Long,RecmdMissionEntity> recmdMissionEntityMap=new HashMap<>();
        ArrayList<RecmdMissionEntity> labelRecommender=labelRecommenderApi.recommend(workerId);
        ArrayList<RecmdMissionEntity> popularRemmender=popularRecommenderApi.recomend(workerId);
        ArrayList<RecmdMissionEntity> tailRecommender=tailRecommenderApi.recommend(workerId);
        ArrayList<RecmdMissionEntity> userInterestRecommender=userInterestRecommenderApi.recommend(workerId);
        ArrayList<RecmdMissionEntity> userTraitRecommender=userTraitRecommenderApi.recommend(workerId);
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        RecmdWeighEntity recmdWeighEntity=workerEntity.getRecmdWeighEntity();
        Double point=0.0;

        //更新workerEntity
        workerEntity.setLabel_recmdMissionEntityList(labelRecommender);
        workerEntity.setUserTrait_recmdMissionEntityList(userTraitRecommender);
        workerEntity.setUserInterest_recmdMissionEntityList(userInterestRecommender);
        recommendation_workerDbHelper.update(workerEntity);


        //labelRecommender
        point=recmdWeighEntity.getLabelRecmd();
        for(RecmdMissionEntity recmdMissionEntity:labelRecommender){
            if(recmdMissionEntityMap.containsKey(recmdMissionEntity.getMissionId())){
                RecmdMissionEntity recmdMissionEntity_repeat=recmdMissionEntityMap.get(recmdMissionEntity.getMissionId());
                RecmdMissionEntity recmdMissionEntity_new=new RecmdMissionEntity(recmdMissionEntity.getMissionId(),
                        recmdMissionEntity_repeat.getEngineName()+", "+recmdMissionEntity.getEngineName(),
                        recmdMissionEntity_repeat.getRecommendReason()+"\n"+recmdMissionEntity.getRecommendReason(),
                        recmdMissionEntity_repeat.getRecommendRefer()+recmdMissionEntity.getRecommendRefer()*point);
            }
            else{
                recmdMissionEntityMap.put(recmdMissionEntity.getMissionId(),recmdMissionEntity);
            }
        }

        //popularRemmender
        point=recmdWeighEntity.getHotRecmd();
        for(RecmdMissionEntity recmdMissionEntity:popularRemmender){
            if(recmdMissionEntityMap.containsKey(recmdMissionEntity.getMissionId())){
                RecmdMissionEntity recmdMissionEntity_repeat=recmdMissionEntityMap.get(recmdMissionEntity.getMissionId());
                RecmdMissionEntity recmdMissionEntity_new=new RecmdMissionEntity(recmdMissionEntity.getMissionId(),
                        recmdMissionEntity_repeat.getEngineName()+", "+recmdMissionEntity.getEngineName(),
                        recmdMissionEntity_repeat.getRecommendReason()+"\n"+recmdMissionEntity.getRecommendReason(),
                        recmdMissionEntity_repeat.getRecommendRefer()+recmdMissionEntity.getRecommendRefer()*point);
            }
            else{
                recmdMissionEntityMap.put(recmdMissionEntity.getMissionId(),recmdMissionEntity);
            }
        }

        //tailRecommender
        point=recmdWeighEntity.getColdRecmd();
        for(RecmdMissionEntity recmdMissionEntity:tailRecommender){
            if(recmdMissionEntityMap.containsKey(recmdMissionEntity.getMissionId())){
                RecmdMissionEntity recmdMissionEntity_repeat=recmdMissionEntityMap.get(recmdMissionEntity.getMissionId());
                RecmdMissionEntity recmdMissionEntity_new=new RecmdMissionEntity(recmdMissionEntity.getMissionId(),
                        recmdMissionEntity_repeat.getEngineName()+", "+recmdMissionEntity.getEngineName(),
                        recmdMissionEntity_repeat.getRecommendReason()+"\n"+recmdMissionEntity.getRecommendReason(),
                        recmdMissionEntity_repeat.getRecommendRefer()+recmdMissionEntity.getRecommendRefer()*point);
            }
            else{
                recmdMissionEntityMap.put(recmdMissionEntity.getMissionId(),recmdMissionEntity);
            }
        }

        //userInterestRecommender
        point=recmdWeighEntity.getInterestRecmd();
        for(RecmdMissionEntity recmdMissionEntity:userInterestRecommender){
            if(recmdMissionEntityMap.containsKey(recmdMissionEntity.getMissionId())){
                RecmdMissionEntity recmdMissionEntity_repeat=recmdMissionEntityMap.get(recmdMissionEntity.getMissionId());
                RecmdMissionEntity recmdMissionEntity_new=new RecmdMissionEntity(recmdMissionEntity.getMissionId(),
                        recmdMissionEntity_repeat.getEngineName()+", "+recmdMissionEntity.getEngineName(),
                        recmdMissionEntity_repeat.getRecommendReason()+"\n"+recmdMissionEntity.getRecommendReason(),
                        recmdMissionEntity_repeat.getRecommendRefer()+recmdMissionEntity.getRecommendRefer()*point);
            }
            else{
                recmdMissionEntityMap.put(recmdMissionEntity.getMissionId(),recmdMissionEntity);
            }
        }

        //userTraitRecommender
        point=recmdWeighEntity.getTraitRecmd();
        for(RecmdMissionEntity recmdMissionEntity:userTraitRecommender){
            if(recmdMissionEntityMap.containsKey(recmdMissionEntity.getMissionId())){
                RecmdMissionEntity recmdMissionEntity_repeat=recmdMissionEntityMap.get(recmdMissionEntity.getMissionId());
                RecmdMissionEntity recmdMissionEntity_new=new RecmdMissionEntity(recmdMissionEntity.getMissionId(),
                        recmdMissionEntity_repeat.getEngineName()+", "+recmdMissionEntity.getEngineName(),
                        recmdMissionEntity_repeat.getRecommendReason()+"\n"+recmdMissionEntity.getRecommendReason(),
                        recmdMissionEntity_repeat.getRecommendRefer()+recmdMissionEntity.getRecommendRefer()*point);
            }
            else{
                recmdMissionEntityMap.put(recmdMissionEntity.getMissionId(),recmdMissionEntity);
            }
        }

        //过滤
        List<Long> unlikedMissionIds=workerEntity.getUninterestedList();
        for(Long unlikedMissionId: unlikedMissionIds){
            if(recmdMissionEntityMap.containsKey(unlikedMissionId)){
                recmdMissionEntityMap.remove(unlikedMissionId);
            }
        }

        //过滤已做过的
        Integer doneNum=workerEntity.getDoneMissionNum();
        DoneMissionEntity[] doneMissionEntities_worked=workerEntity.getDoneMissionEntities();
        Integer doingNum=workerEntity.getDoingMissionsNum();
        DoingMissionEntity[] doingMissionEntities_worked=workerEntity.getDoingMissionEntities();
        ArrayList<Long> mission_worked=new ArrayList<>();
        for(int i=0;i<doingNum;i++){
            mission_worked.add(doingMissionEntities_worked[i].getMissionId());
        }
        for(int i=0;i<doneNum;i++){
            mission_worked.add(doneMissionEntities_worked[i].getMissionId());
        }
        for(Long workedMissionId: mission_worked){
            if(recmdMissionEntityMap.containsKey(workedMissionId)){
                recmdMissionEntityMap.remove(workedMissionId);
            }
        }

        //推荐列表排序
        List<Map.Entry<Long,RecmdMissionEntity>> list = new ArrayList<Map.Entry<Long,RecmdMissionEntity>>(recmdMissionEntityMap.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Long,RecmdMissionEntity>>() {
            //排序
            public int compare(Map.Entry<Long, RecmdMissionEntity> o1,
                               Map.Entry<Long, RecmdMissionEntity> o2) {
                return o2.getValue().getRecommendRefer().compareTo(o1.getValue().getRecommendRefer());
            }

        });

        //输出推荐列表
        ArrayList<RecmdMissionEntity> recmdMissionEntities_result=new ArrayList<>();
        int i=0;
        for(Map.Entry<Long,RecmdMissionEntity> mapping: list){
            if(i>=6){
                break;
            }

            recmdMissionEntities_result.add(mapping.getValue());
            i++;
        }

        return recmdMissionEntities_result;
    }
}
