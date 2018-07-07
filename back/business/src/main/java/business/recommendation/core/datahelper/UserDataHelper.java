package business.recommendation.core.datahelper;

import business.common.entity.workerentity.WorkerEntity;
import business.common.entity.workerentity.WorkerInfoEntity;
import business.common.repository.WorkerRepository;
import business.recommendation.core.datamodel.EntityModel;
import business.recommendation.core.datamodel.InputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Component
public class UserDataHelper {

    @Autowired
    private WorkerRepository workerRepository;

    private ArrayList<WorkerEntity> workerEntityArrayList;

    private int cnt = 0;//凡到2，就说明两种结果都被调用，则重新载入数据

    public void init(){
        if (workerEntityArrayList == null || cnt == 2){
            cnt = 0;
            workerEntityArrayList = new ArrayList<>();
            Iterator<WorkerEntity> workerEntityIterator = workerRepository.findAll().iterator();
            while (workerEntityIterator.hasNext()){
                workerEntityArrayList.add(workerEntityIterator.next());
            }
        }
        cnt++;
    }

    @Transactional
    public InputModel getWorkerTraitData(){
        init();
        ArrayList<EntityModel> entityModelArrayList = new ArrayList<>();

        for (int i = 0; i< workerEntityArrayList.size(); ++i){
            ArrayList<Double> doubles = new ArrayList<>();
            Map<Integer,WorkerInfoEntity> map = workerEntityArrayList.get(i).getWorkerInfo();
            Integer[] keys = map.keySet().toArray(new Integer[map.size()]);
            for (int j = 0; j < keys.length; ++j){
                doubles.add(Double.valueOf(map.get(keys[j]).getTraitRefer()));
            }
            EntityModel entityModel = new EntityModel(workerEntityArrayList.get(i).getWorkerId(),doubles);
            entityModelArrayList.add(entityModel);
        }

        InputModel inputModel = new InputModel("workerTrait",entityModelArrayList);
        return inputModel;
    }

    @Transactional
    public InputModel getWorkerInterestData(){
        init();
        ArrayList<EntityModel> entityModelArrayList = new ArrayList<>();

        for (int i = 0; i< workerEntityArrayList.size(); ++i){
            ArrayList<Double> doubles = new ArrayList<>();
            Map<Integer,WorkerInfoEntity> map = workerEntityArrayList.get(i).getWorkerInfo();
            Integer[] keys = map.keySet().toArray(new Integer[map.size()]);
            for (int j = 0; j < keys.length; ++j){
                doubles.add(Double.valueOf(map.get(keys[j]).getInterestRefer()));
            }
            EntityModel entityModel = new EntityModel(workerEntityArrayList.get(i).getWorkerId(),doubles);
            entityModelArrayList.add(entityModel);
        }

        InputModel inputModel = new InputModel("workerInterest",entityModelArrayList);
        return inputModel;
    }
}
