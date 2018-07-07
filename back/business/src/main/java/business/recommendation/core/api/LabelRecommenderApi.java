package business.recommendation.core.api;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.RecmdMissionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface LabelRecommenderApi {

    ArrayList<RecmdMissionEntity> recommend(Long workerId);
}
