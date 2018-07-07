package business.mission.missionmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface AssignMissionService {

    boolean assignMission(Long workerId, String kind, Long missionId, String type) throws Exception;
}
