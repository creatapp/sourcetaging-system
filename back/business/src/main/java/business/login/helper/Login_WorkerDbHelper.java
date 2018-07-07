package business.login.helper;

import business.common.entity.workerentity.WorkerEntity;
import org.springframework.stereotype.Component;

@Component
public interface Login_WorkerDbHelper {

    void add(WorkerEntity workerEntity);
}
