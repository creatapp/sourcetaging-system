package business.login.helper.impl;

import business.common.entity.workerentity.WorkerEntity;
import business.common.repository.WorkerRepository;
import business.login.helper.Login_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login_WorkerDbHelperImpl implements Login_WorkerDbHelper {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public void add(WorkerEntity workerEntity) {
        workerRepository.save(workerEntity);
    }
}
