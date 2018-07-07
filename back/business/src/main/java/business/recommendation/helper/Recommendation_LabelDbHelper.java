package business.recommendation.helper;

import business.common.entity.missionentity.MissionLabelEntity;
import org.springframework.stereotype.Component;

@Component
public interface Recommendation_LabelDbHelper {
    boolean update(MissionLabelEntity missionLabelEntity);

    MissionLabelEntity findByLabelId(Long labelId);
}
