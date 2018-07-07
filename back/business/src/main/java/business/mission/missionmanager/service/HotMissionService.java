package business.mission.missionmanager.service;

import business.common.entity.missionentity.HotMissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface HotMissionService {
    HotMissionEntity[] getAll();

    boolean updateAll(HotMissionEntity[] hotMissionEntities);
}
