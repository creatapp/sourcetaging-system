package business.user.helper.impl;

import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.common.repository.WorkerRepository;
import business.user.helper.User_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class User_WorkerDbHelperImpl implements User_WorkerDbHelper {

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

    @Override
    public WorkerEntity[] findAll() {
        Iterable<WorkerEntity> workerEntityIterable = workerRepository.findAll();
        Iterator<WorkerEntity> workerEntityIterator = workerEntityIterable.iterator();

        ArrayList<WorkerEntity> workerEntities = new ArrayList<>();
        while (workerEntityIterator.hasNext()){
            workerEntities.add(workerEntityIterator.next());
        }
        WorkerEntity[] result=new WorkerEntity[workerEntities.size()];
        for(int i=0;i<workerEntities.size();i++){
            result[i]=workerEntities.get(i);
        }
        return result;
    }

    @Override
    public boolean exists(Long workerId) {
        return workerRepository.existsById(workerId);
    }
}
