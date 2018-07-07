package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.userentity.UserEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.mission.helper.Mission_UserDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.missionmanager.service.GetParticipantsService;
import business.mission.missionmanager.vo.ParticipantsReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class GetParticipantsImpl implements GetParticipantsService {

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_UserDbHelper missionUserDbHelper;

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;

    @Override
    public ParticipantsReturnVO[] getParticipants(Long missionId) throws Exception {
        MissionEntity missionEntity = missionMissionDbHelper.findById(missionId);

        Long[] participants= missionEntity.getParticipants();

        ParticipantsReturnVO[] participantsReturnVOS =new ParticipantsReturnVO[participants.length];

        for(int i=0;i<participants.length;i++){
            UserEntity userEntity= missionUserDbHelper.findById(participants[i]);
            WorkerEntity workerEntity= missionWorkerDbHelper.findById(participants[i]);
            participantsReturnVOS[i]=new ParticipantsReturnVO(userEntity.getId(),userEntity.getNickName(),userEntity.getEmail(),
                    workerEntity.getPoints());
        }

        return participantsReturnVOS;
    }
}
