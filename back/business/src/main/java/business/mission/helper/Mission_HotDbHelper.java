package business.mission.helper;

import business.common.entity.missionentity.HotMissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mission_HotDbHelper {
    HotMissionEntity[] getAll();

    boolean updateAll(HotMissionEntity[] hotMissionEntities);
}
