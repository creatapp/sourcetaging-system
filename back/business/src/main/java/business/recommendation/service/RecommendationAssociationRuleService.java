package business.recommendation.service;

import business.recommendation.vo.AssociationRuleVO;
import org.springframework.stereotype.Component;

@Component
public interface RecommendationAssociationRuleService {

    //关联项挖掘:返回工人的潜在挖掘项以及支持度、置信度、提升度
    AssociationRuleVO[] getAssociationRule(Long workerId);
}
