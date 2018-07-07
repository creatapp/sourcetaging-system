package business.user.helper;

import business.common.entity.workerentity.WorkerEntity;
import org.springframework.stereotype.Component;

@Component
public interface User_WorkerDbHelper {

    boolean update(WorkerEntity workerEntity);

    WorkerEntity findById(long id);

    WorkerEntity[] findAll();

    boolean exists(Long workerId);
}
