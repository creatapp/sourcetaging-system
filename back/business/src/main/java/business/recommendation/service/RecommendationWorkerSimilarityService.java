package business.recommendation.service;

import business.recommendation.vo.WorkerSimilarityVO;
import org.springframework.stereotype.Component;

@Component
public interface RecommendationWorkerSimilarityService {

    //相似工人:返回工人相似的工人列表，包括工人Id、昵称、排名、相似原因【兴趣或是能力】以及与该工人的相似度
    WorkerSimilarityVO[] listWorkerSimilarity(Long workerId);
}
