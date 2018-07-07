package business.mission.helper;

import business.common.entity.missionentity.MissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mission_MissionDbHelper {

    boolean update(MissionEntity missionEntity);

    MissionEntity findById(long missionId);

    Long add(MissionEntity missionEntity);

    MissionEntity[] listAllMissions();

    MissionEntity[] listUnfinishedMissions();

}
