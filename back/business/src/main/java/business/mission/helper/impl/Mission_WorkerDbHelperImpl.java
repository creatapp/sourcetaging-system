package business.mission.helper.impl;

import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.common.repository.WorkerRepository;
import business.mission.helper.Mission_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Mission_WorkerDbHelperImpl implements Mission_WorkerDbHelper {

    @Autowired
    private WorkerRepository workerRepository;

    public boolean update(WorkerEntity workerEntity){
        workerRepository.save(workerEntity);
        return true;
    }

    public WorkerEntity findById(long id){
        Optional<WorkerEntity> workerEntityOptional = workerRepository.findById(id);
        if(workerEntityOptional.isPresent())return workerEntityOptional.get();
        else throw new LogicException("未找到该工人");
    }
}
