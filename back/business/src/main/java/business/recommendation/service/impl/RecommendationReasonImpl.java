package business.recommendation.service.impl;

import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.api.PopularRecommenderApi;
import business.recommendation.core.api.TailRecommenderApi;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import business.recommendation.service.RecommendationReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationReasonImpl implements RecommendationReasonService {

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Autowired
    private PopularRecommenderApi popularRecommenderApi;

    @Autowired
    private TailRecommenderApi tailRecommenderApi;

    @Override
    @Transactional
    public String getReason(Long workerId, Long missionId) {

        String reason="";

        WorkerEntity workerEntity=recommendation_workerDbHelper.findById(workerId);
        List<RecmdMissionEntity> label_recmdMissionEntityList=workerEntity.getLabel_recmdMissionEntityList();
        //List<RecmdMissionEntity> tail_recmdMissionEntityList=workerEntity.getTail_recmdMissionEntityList();
        List<RecmdMissionEntity> userInterest_recmdMissionEntityList=workerEntity.getUserInterest_recmdMissionEntityList();
        List<RecmdMissionEntity> userTrait_recmdMissionEntityList=workerEntity.getUserTrait_recmdMissionEntityList();

        for(RecmdMissionEntity recmdMissionEntity: label_recmdMissionEntityList){
            if(recmdMissionEntity.getMissionId().equals(missionId)){
                reason=reason+"\n"+"该任务与你最近做的任务相似";
            }
        }

//        for(RecmdMissionEntity recmdMissionEntity: tail_recmdMissionEntityList){
//            if(recmdMissionEntity.getMissionId().equals(missionId)){
//                reason=reason+"\n"+"";
//            }
//        }

        for(RecmdMissionEntity recmdMissionEntity: userInterest_recmdMissionEntityList){
            if(recmdMissionEntity.getMissionId().equals(missionId)){
                reason=reason+"\n"+"与你兴趣相同的人做过这些任务";
            }
        }

        for(RecmdMissionEntity recmdMissionEntity: userTrait_recmdMissionEntityList){
            if(recmdMissionEntity.getMissionId().equals(missionId)){
                reason=reason+"\n"+"与你能力相似的人做过这些任务";
            }
        }

        ArrayList<RecmdMissionEntity> popularRemmender=popularRecommenderApi.recomend(workerId);
        ArrayList<RecmdMissionEntity> tailRecommender=tailRecommenderApi.recommend(workerId);

        for(RecmdMissionEntity recmdMissionEntity: popularRemmender){
            if(recmdMissionEntity.getMissionId().equals(missionId)){
                reason=reason+"\n"+"此任务最近很热门";
            }
        }

        for(RecmdMissionEntity recmdMissionEntity: popularRemmender){
            if(recmdMissionEntity.getMissionId().equals(missionId)){
                reason=reason+"\n"+"你也许会尝试这些新任务";
            }
        }



        return reason;
    }
}
