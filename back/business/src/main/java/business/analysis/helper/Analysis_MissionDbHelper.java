package business.analysis.helper;

import business.common.entity.missionentity.MissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface Analysis_MissionDbHelper {

    boolean udpate(MissionEntity missionEntity);

    MissionEntity findById(long missionId);

}
