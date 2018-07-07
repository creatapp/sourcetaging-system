package business.recommendation.service;

import org.springframework.stereotype.Component;

@Component
public interface RecommendationReasonService {
    String getReason(Long workerId,Long missionId);
}
