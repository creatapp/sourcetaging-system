package business.recommendation.core.api.impl;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.missionentity.MissionLabelEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.api.LabelRecommenderApi;
import business.recommendation.helper.Recommendation_LabelDbHelper;
import business.recommendation.helper.Recommendation_MissionDbHelper;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class LabelRecommenderApiImpl implements LabelRecommenderApi {

    @Autowired
    private Recommendation_LabelDbHelper recommendation_labelDbHelper;

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Autowired
    private Recommendation_MissionDbHelper recommendation_missionDbHelper;

    @Override
    @Transactional
    public ArrayList<RecmdMissionEntity> recommend(Long workerId) {
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        ArrayList<RecmdMissionEntity> recmdMissionEntities=new ArrayList<>();
        Map<Long,Double> result=new HashMap<>();


        //获取最新任务
        Long missionId_Latest;
        Date doing=new Date();
        Date done=new Date();
        DoneMissionEntity[] doneMissionEntities=workerEntity.getDoneMissionEntities();
        DoingMissionEntity[] doingMissionEntities=workerEntity.getDoingMissionEntities();
        Integer doneMissionNum=workerEntity.getDoneMissionNum();
        Integer doingMissionNum=workerEntity.getDoingMissionsNum();
        if(doingMissionNum==0){
            if(doneMissionNum==0){
                return recmdMissionEntities;
            }
            missionId_Latest=doneMissionEntities[doneMissionNum-1].getMissionId();
        }
        else{
            doing=doingMissionEntities[doingMissionNum-1].getInitTime();
            if(doneMissionNum==0){
                missionId_Latest=doingMissionEntities[doingMissionNum-1].getMissionId();
            }
            else{
                done=doneMissionEntities[doneMissionNum-1].getInitTime();
                if(doing.before(done)){
                    missionId_Latest=doneMissionEntities[doneMissionNum-1].getMissionId();
                }
                else{
                    missionId_Latest=doingMissionEntities[doingMissionNum-1].getMissionId();
                }
            }
        }

        //得到相同主题任务
        MissionEntity missionEntity=recommendation_missionDbHelper.findById(missionId_Latest);
        Set<Integer> labels=missionEntity.getLabel();
        for(Integer label_1: labels){
            Long label=label_1.longValue();
            MissionLabelEntity missionLabelEntity=recommendation_labelDbHelper.findByLabelId(label);
            Set<Long> missionIdList=missionLabelEntity.getMissionIdList();
            for(Long missionId: missionIdList){
                if(result.containsKey(missionId)){
                    result.put(missionId,1-(1-result.get(missionId))*(1-result.get(missionId)));
                }
                else{
                    result.put(missionId,0.5);
                }
            }
        }

        //数据注入
        for(Long key: result.keySet()){
            RecmdMissionEntity recmdMissionEntity=new RecmdMissionEntity(key,"LabelRecommender",
                    "你最近做过相似任务",result.get(key));
            recmdMissionEntities.add(recmdMissionEntity);
        }

        //过滤
        List<Long> unInterestedMissions=workerEntity.getUninterestedList();
        for(Long missionId: unInterestedMissions){
            for(int i=0;i<recmdMissionEntities.size();i++){
                if(recmdMissionEntities.get(i).getMissionId().equals(missionId)){
                    recmdMissionEntities.remove(i);
                }
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
            for(int m=0;m<recmdMissionEntities.size();m++){
                if(recmdMissionEntities.get(m).getMissionId().equals(workedMissionId)){
                    recmdMissionEntities.remove(m);
                }
            }
        }

        return recmdMissionEntities;
    }
}
