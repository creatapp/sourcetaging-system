package business.recommendation.service.impl;

import business.common.entity.userentity.UserEntity;
import business.common.entity.workerentity.SimWorkerEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.helper.Recommendation_UserDbHelper;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import business.recommendation.service.RecommendationWorkerSimilarityService;
import business.recommendation.vo.WorkerSimilarityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecommendationWorkerSimilarityImpl implements RecommendationWorkerSimilarityService {

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Autowired
    private Recommendation_UserDbHelper recommendation_userDbHelper;

    @Override
    @Transactional
    public WorkerSimilarityVO[] listWorkerSimilarity(Long workerId) {
        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        List<SimWorkerEntity> recmdTraitSimWorker=workerEntity.getRecmdTraitSimWorker();
        List<SimWorkerEntity> recmdInterestSimWorker=workerEntity.getRecmdInterestSimWorker();
        WorkerSimilarityVO[] workerSimilarityVOS=new WorkerSimilarityVO[10];
        for(int i=0;i<5;i++){
            UserEntity userEntity=recommendation_userDbHelper.findById(workerId);
            workerSimilarityVOS[i]=new WorkerSimilarityVO(recmdTraitSimWorker.get(i).getWorkerId(),userEntity.getNickName(),workerEntity.getRank(),1,recmdTraitSimWorker.get(i).getSimRefer());
        }
        for(int i=5;i<10;i++){
            UserEntity userEntity=recommendation_userDbHelper.findById(workerId);
            WorkerSimilarityVO workerSimilarityVO=new WorkerSimilarityVO(recmdInterestSimWorker.get(i-5).getWorkerId(),userEntity.getNickName(),workerEntity.getRank(),0,recmdInterestSimWorker.get(i-5).getSimRefer());
            workerSimilarityVOS[i]=workerSimilarityVO;
        }

        return workerSimilarityVOS;
    }
}
