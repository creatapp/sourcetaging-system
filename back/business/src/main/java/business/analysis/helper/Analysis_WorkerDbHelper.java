package business.analysis.helper;

import business.common.entity.workerentity.WorkerEntity;
import org.springframework.stereotype.Component;

@Component
public interface Analysis_WorkerDbHelper {

    boolean update(WorkerEntity workerEntity);

    WorkerEntity findById(long workerId);
}
