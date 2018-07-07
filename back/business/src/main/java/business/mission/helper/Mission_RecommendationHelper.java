package business.mission.helper;

import business.common.entity.missionentity.MissionEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mission_RecommendationHelper {

    MissionEntity[] recommendMissions(Long workerId);
}
