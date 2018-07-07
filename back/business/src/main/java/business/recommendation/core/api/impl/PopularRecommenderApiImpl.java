package business.recommendation.core.api.impl;

import business.common.entity.missionentity.HotMissionEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.recommendation.core.api.PopularRecommenderApi;
import business.recommendation.helper.Recommendation_HotDbHelper;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopularRecommenderApiImpl implements PopularRecommenderApi {

    @Autowired
    private Recommendation_HotDbHelper recommendation_hotDbHelper;

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Override
    @Transactional
    public ArrayList<RecmdMissionEntity> recomend(Long workerId) {

        ArrayList<RecmdMissionEntity> recmdMissionEntities=new ArrayList<>();
        HotMissionEntity[] hotMissionEntities=recommendation_hotDbHelper.getAll();
        for(int i=0;i<10&&i<hotMissionEntities.length;i++){
            RecmdMissionEntity recmdMissionEntity=new RecmdMissionEntity(hotMissionEntities[i].getMissionId(),
                    "PopularRecommender","该任务最近很火",hotMissionEntities[i].getHotRefer());
            recmdMissionEntities.add(recmdMissionEntity);
        }

        //过滤
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        List<Long> unInterestedMissions=new ArrayList<>();
        try{
            unInterestedMissions=workerEntity.getUninterestedList();
        }catch(Exception e){

        }


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

        /*ArrayList<RecommendDTO> recommendDTOS=new ArrayList<>();

        MissionEntity[] missionEntities=recommendation_missionDbHelper.listUnfinishedMissions();
        //热度
        Double[] point=new Double[missionEntities.length];
        //热度对应任务
        Integer[] pointer=new Integer[missionEntities.length];

        //初始化对应关系
        for(int i=0;i<pointer.length;i++){
            pointer[i]=i;
        }

        for(int j=0;j<missionEntities.length;j++){
            //计算时间差
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date startDate = formatter.parse(missionEntities[j].getStartDate(), pos);

            //point[j]=missionEntities[j].getParticipants().length/
        }

        //排序
        for(int m=0;m<pointer.length;m++){
            for(int n=0;n<pointer.length-1;n++){
                if(point[pointer[n]]<point[pointer[n+1]]) {
                    int index = pointer[n];
                    pointer[n] = pointer[n + 1];
                    pointer[n + 1] = index;
                }
            }
        }

        for(int i=0;i<10;i++){
            RecommendDTO recommendDTO=new RecommendDTO();
            recommendDTOS.add(recommendDTO);
        }

        return null;*/
    }
}
