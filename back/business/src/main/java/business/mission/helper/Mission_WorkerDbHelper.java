package business.mission.helper;

import business.common.entity.workerentity.WorkerEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mission_WorkerDbHelper {

    boolean update(WorkerEntity workerEntity);

    WorkerEntity findById(long id);
}
