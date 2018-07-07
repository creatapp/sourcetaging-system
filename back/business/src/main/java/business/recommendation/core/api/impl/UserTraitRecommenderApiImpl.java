package business.recommendation.core.api.impl;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.SimWorkerEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.api.UserTraitRecommenderApi;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserTraitRecommenderApiImpl implements UserTraitRecommenderApi {

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Override
    @Transactional
    public ArrayList<RecmdMissionEntity> recommend(Long workerId) {
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        List<SimWorkerEntity> recmdTraitSimWorker=workerEntity.getRecmdTraitSimWorker();
        if(recmdTraitSimWorker.size()==0){
            return new ArrayList<RecmdMissionEntity>();
        }
        else{
            //找到所有符合任务
            Map<Long,Double> missions=new HashMap<>();
            for(int i=0;i<recmdTraitSimWorker.size();i++){
                SimWorkerEntity simWorkerEntity=recmdTraitSimWorker.get(i);
                WorkerEntity workerEntity_Sim=recommendation_workerDbHelper.findById(simWorkerEntity.getWorkerId());
                DoneMissionEntity[] doneMissionEntities=workerEntity_Sim.getDoneMissionEntities();
                Integer doneMissionNum=workerEntity_Sim.getDoneMissionNum();
                for(int m=doneMissionNum-1;m>=0&&m>doneMissionNum-4;m--){
                    if(missions.containsKey(doneMissionEntities[m].getMissionId())){
                        missions.put(doneMissionEntities[m].getMissionId(),missions.get(doneMissionEntities[m].getMissionId())+
                                (1-missions.get(doneMissionEntities[m].getMissionId()))*simWorkerEntity.getSimRefer());
                    }
                    else{
                        missions.put(doneMissionEntities[m].getMissionId(),simWorkerEntity.getSimRefer());
                    }
                }
            }

            //排序并注入
            ArrayList<RecmdMissionEntity> recmdMissionEntities=new ArrayList<>();
            List<Map.Entry<Long,Double>> list = new ArrayList<Map.Entry<Long,Double>>(missions.entrySet());
            Collections.sort(list,new Comparator<Map.Entry<Long,Double>>() {
                //升序排序
                public int compare(Map.Entry<Long, Double> o1,
                                   Map.Entry<Long, Double> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }

            });

            for(Map.Entry<Long,Double> mapping: list){

                recmdMissionEntities.add(new RecmdMissionEntity(mapping.getKey(),"UserInterestRecommender","与你能力相似的人做过这些任务",mapping.getValue()));
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
}
