package business.recommendation.service;

import business.recommendation.vo.WorkerRecmdInfoVO;
import org.springframework.stereotype.Component;

@Component
public interface RecommendationWorkerRecmdInfoService {

    //工人数据：返回工人的兴趣、能力指数的列表
    WorkerRecmdInfoVO[] listWorkerRecmdInfo(Long workerId);
}
