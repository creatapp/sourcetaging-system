package business.mission.missionmanager.service;

import business.mission.missionmanager.vo.ParticipantsReturnVO;
import org.springframework.stereotype.Component;

@Component
public interface GetParticipantsService {

    ParticipantsReturnVO[] getParticipants(Long missionId) throws Exception;
}
