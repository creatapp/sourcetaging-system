package business.recommendation.service;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.RecmdMissionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface RecommendationService {

    //RecommendDTO定义在common/dto下
    ArrayList<RecmdMissionEntity> recommendPopular(Long workerId);//4

    ArrayList<RecmdMissionEntity> recommendPersonal(Long workerId);//6
}
