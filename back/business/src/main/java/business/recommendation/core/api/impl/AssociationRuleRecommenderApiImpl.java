package business.recommendation.core.api.impl;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.recommendation.core.api.AssociationRuleRecommenderApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssociationRuleRecommenderApiImpl implements AssociationRuleRecommenderApi {

    @Override
    public ArrayList<RecmdMissionEntity> recommend(Long workerId) {
        return null;
    }
}
