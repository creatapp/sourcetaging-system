package business.recommendation.helper.impl;

import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.common.repository.WorkerRepository;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class Recommendation_WorkerDbHelperImpl implements Recommendation_WorkerDbHelper {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public boolean update(WorkerEntity workerEntity){
        workerRepository.save(workerEntity);
        return true;
    }

    @Override
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
}
