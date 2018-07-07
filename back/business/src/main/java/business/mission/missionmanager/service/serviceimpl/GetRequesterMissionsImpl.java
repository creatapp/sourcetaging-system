package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.requesterentity.RequesterEntity;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_RequesterDbHelper;
import business.mission.missionmanager.service.GetRequesterMissionsService;
import business.mission.missionmanager.vo.MissionInfoReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class GetRequesterMissionsImpl implements GetRequesterMissionsService {

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_RequesterDbHelper missionRequesterDbHelper;

    @Override
    public MissionInfoReturnVO[] getRequesterMission(Long requesterId) throws Exception {

        RequesterEntity requesterEntity= missionRequesterDbHelper.findById(requesterId);

        MissionInfoReturnVO[] missionInfoReturnVOS =new MissionInfoReturnVO[requesterEntity.getReleasedMissionsNum()];

        for(int i=0;i<requesterEntity.getReleasedMissionsNum();i++){
            MissionEntity missionEntity = missionMissionDbHelper.findById(requesterEntity.getReleasedMissionsList()[i]);

            missionInfoReturnVOS[i]=new MissionInfoReturnVO(missionEntity,null);
        }


        return missionInfoReturnVOS;
    }
}
