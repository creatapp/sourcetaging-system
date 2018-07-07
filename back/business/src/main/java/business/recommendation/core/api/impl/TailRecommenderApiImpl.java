package business.recommendation.core.api.impl;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.HotMissionEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.api.TailRecommenderApi;
import business.recommendation.helper.Recommendation_HotDbHelper;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TailRecommenderApiImpl implements TailRecommenderApi {

    @Autowired
    private Recommendation_HotDbHelper recommendation_hotDbHelper;

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Override
    @Transactional
    public ArrayList<RecmdMissionEntity> recommend(Long workerId) {
        ArrayList<RecmdMissionEntity> recmdMissionEntities=new ArrayList<>();
        HotMissionEntity[] hotMissionEntities=recommendation_hotDbHelper.getAll();



        for(int i=hotMissionEntities.length-1;i>0&&i>hotMissionEntities.length-11;i--){

            RecmdMissionEntity recmdMissionEntity=new RecmdMissionEntity(hotMissionEntities[i].getMissionId(),
                    "PopularRecommender","你也许会尝试这些新任务",hotMissionEntities[i].getHotRefer());
            recmdMissionEntities.add(recmdMissionEntity);
        }

        //过滤
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
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
