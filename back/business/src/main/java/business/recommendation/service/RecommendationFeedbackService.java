package business.recommendation.service;

import org.springframework.stereotype.Component;

@Component
public interface RecommendationFeedbackService {

    //event 是用户对推荐  感兴趣、不感兴趣、只是浏览 分别0、1、2
    void adjust(Long workerId, Long missionId, int event);
}
