package business.mission.picturesmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface ChangeMissionService {

    boolean changeMission(Long missionId, Long workerId, String kind);
}
