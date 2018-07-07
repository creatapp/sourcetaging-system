package business.recommendation.service.impl;

import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.entity.workerentity.WorkerInfoEntity;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import business.recommendation.service.RecommendationWorkerRecmdInfoService;
import business.recommendation.vo.WorkerRecmdInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class RecommendationWorkerRecmdInfoImpl implements RecommendationWorkerRecmdInfoService {

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Override
    @Transactional
    public WorkerRecmdInfoVO[] listWorkerRecmdInfo(Long workerId) {
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        Map<Integer,WorkerInfoEntity> workerInfo=workerEntity.getWorkerInfo();
        WorkerRecmdInfoVO[] workerRecmdInfoVOS=new WorkerRecmdInfoVO[workerInfo.size()];
        Set<Integer> keySet = workerInfo.keySet();
        int i=0;
        for(Iterator<Integer> iterator = keySet.iterator(); iterator.hasNext();){
            Integer key = iterator.next();
            WorkerInfoEntity value = workerInfo.get(key);

            workerRecmdInfoVOS[i]=new WorkerRecmdInfoVO(value.getInterestRefer(),value.getTraitRefer());
            i++;
        }

        return workerRecmdInfoVOS;
    }
}
