package business.mission.missionmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface SignUpNewMissionService {

    String signUpMission(Long workerId, Long missionId, String kind) throws Exception;
}
