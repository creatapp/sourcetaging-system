package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.mission.helper.Mission_RecommendationHelper;
import business.mission.missionmanager.service.ListNewMissionListService;
import business.mission.missionmanager.vo.NewMissionInfoReturnVO;
import business.user.helper.User_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class ListNewMissionListImpl implements ListNewMissionListService {

    @Autowired
    private Mission_RecommendationHelper mission_recommendationHelper;
    @Autowired
    private User_WorkerDbHelper user_workerDbHelper;


    @Override
    public NewMissionInfoReturnVO[] listNewMissionList(Long workerId) throws Exception {

        MissionEntity[] missionEntities = mission_recommendationHelper.recommendMissions(workerId);
        if(missionEntities.length==0){
            return new NewMissionInfoReturnVO[0];
        }

        WorkerEntity workerEntity=user_workerDbHelper.findById(workerId);

        Set<NewMissionInfoReturnVO> newMissionInfoReturnVOS1=new HashSet<>();

        for(int i = 0; i< missionEntities.length; i++){
            boolean tagLeft=true;
            boolean input=true;
            Integer[] classLabel=missionEntities[i].getLabel().toArray(new Integer[missionEntities[i].getLabel().size()]);
            for(int j=0;j<workerEntity.getDoneMissionNum();j++){
                if (workerEntity.getDoneMissionEntities()[j].getMissionId()==missionEntities[i].getMissionId()){
                    input=false;
                }
            }
            if(missionEntities[i].getPlanToTagQueue().size()==0){
                tagLeft=false;
            }
            if (input)
            newMissionInfoReturnVOS1.add(new NewMissionInfoReturnVO(missionEntities[i].getMissionId(), missionEntities[i].getTitle(), missionEntities[i].getDescription(),
                    missionEntities[i].getType(), missionEntities[i].getTagPrice(), missionEntities[i].getCheckPrice(),
                    classLabel,tagLeft));
        }
        NewMissionInfoReturnVO[] newMissionInfoReturnVOS=newMissionInfoReturnVOS1.toArray(
                new NewMissionInfoReturnVO[newMissionInfoReturnVOS1.size()]);


        return newMissionInfoReturnVOS;
    }
}