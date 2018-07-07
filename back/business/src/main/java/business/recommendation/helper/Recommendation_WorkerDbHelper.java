package business.recommendation.helper;

import business.common.entity.workerentity.WorkerEntity;
import org.springframework.stereotype.Component;

@Component
public interface Recommendation_WorkerDbHelper {

    boolean update(WorkerEntity workerEntity);

    WorkerEntity findById(long id);

    WorkerEntity[] findAll();
}
