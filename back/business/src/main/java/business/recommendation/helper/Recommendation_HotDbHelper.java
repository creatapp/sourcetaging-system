package business.recommendation.helper;

import business.common.entity.missionentity.HotMissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface Recommendation_HotDbHelper {
    HotMissionEntity[] getAll();

    boolean updateAll(HotMissionEntity[] hotMissionEntities);
}
