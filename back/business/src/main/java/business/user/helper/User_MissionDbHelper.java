package business.user.helper;

import business.common.entity.missionentity.MissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface User_MissionDbHelper {

    boolean update(MissionEntity missionEntity);

    MissionEntity findById(long missionId);

    Long add(MissionEntity missionEntity);

}
