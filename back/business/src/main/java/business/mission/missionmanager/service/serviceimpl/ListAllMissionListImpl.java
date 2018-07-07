package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.repository.MissionRepository;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.missionmanager.service.ListAllMissionListService;
import business.mission.missionmanager.vo.MissionInfoReturnVO;
import business.mission.missionmanager.vo.MissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Queue;

@Transactional
@Service
public class ListAllMissionListImpl implements ListAllMissionListService {

    @Autowired
    private Mission_MissionDbHelper mission_missionDbHelper;

    @Override
    public MissionInfoReturnVO[] ListAllMissionList() throws Exception {
        MissionEntity[] missionEntities = mission_missionDbHelper.listAllMissions();
        if(missionEntities[0] == null || missionEntities[0].getMissionId() <= 0){
            return null;
        }

        MissionInfoReturnVO[] missionInfoReturnVOS =new MissionInfoReturnVO[missionEntities.length];

        for(int i = 0; i< missionEntities.length; i++){
            missionInfoReturnVOS[i]=new MissionInfoReturnVO(missionEntities[i],null);
        }

        MissionVO missionVO=new MissionVO(missionInfoReturnVOS);
        return missionInfoReturnVOS;
    }
}