package business.analysis.helper.impl;

import business.analysis.helper.Analysis_WorkerDbHelper;
import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.common.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Analysis_WorkerDbHelperImpl implements Analysis_WorkerDbHelper {

    @Autowired
    private WorkerRepository workerRepository;

    public boolean update(WorkerEntity workerEntity){
        workerRepository.save(workerEntity);
        return true;
    }

    public WorkerEntity findById(long workerId){
        Optional<WorkerEntity> workerEntityOptional = workerRepository.findById(workerId);
        if(workerEntityOptional.isPresent())return workerEntityOptional.get();
        else throw new LogicException("未找到该工人");
    }
}
