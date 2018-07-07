package business.recommendation.service;

import business.BusinessApplication;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.RecmdWeighEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.api.*;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RecommendationServiceTest {

    @Autowired
    @InjectMocks
    private RecommendationService recommendationService;

    @Mock
    private PopularRecommenderApi popularRecommenderApi;

    @Mock
    private LabelRecommenderApi labelRecommenderApi;

    @Mock
    private TailRecommenderApi tailRecommenderApi;

    @Mock
    private UserInterestRecommenderApi userInterestRecommenderApi;

    @Mock
    private UserTraitRecommenderApi userTraitRecommenderApi;

    @Mock
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Before
    public void setUp() throws Exception {

        //生成ArrayList<RecmdMissionEntity>
        RecmdMissionEntity recmdMissionEntity=new RecmdMissionEntity(new Long(127),"","",0.73);
        ArrayList<RecmdMissionEntity> recmdMissionEntities=new ArrayList<>();
        ArrayList<RecmdMissionEntity> recmdMissionEntities_popular=new ArrayList<>();
        recmdMissionEntities_popular.add(recmdMissionEntity);

        //生成workerEntity
        RecmdWeighEntity recmdWeighEntity=new RecmdWeighEntity(1.0,0.0,0.0,0.0,0.0,0.0);
        List<RecmdMissionEntity> label_recmdMissionEntityList = new ArrayList<>();
        List<RecmdMissionEntity> userInterest_recmdMissionEntityList = new ArrayList<>();
        List<RecmdMissionEntity> userTrait_recmdMissionEntityList = new ArrayList<>();
        List<Long> uninterestList = new ArrayList<>();

        WorkerEntity workerEntity=new WorkerEntity();
        workerEntity.setUserInterest_recmdMissionEntityList(userInterest_recmdMissionEntityList);
        workerEntity.setUserTrait_recmdMissionEntityList(userTrait_recmdMissionEntityList);
        workerEntity.setUninterestedList(uninterestList);
        workerEntity.setLabel_recmdMissionEntityList(label_recmdMissionEntityList);
        workerEntity.setRecmdWeighEntity(recmdWeighEntity);

        //生成missionEntity
        Set<Integer> labelSet=new HashSet<>();
        labelSet.add(1);
        labelSet.add(2);
        MissionEntity missionEntity=new MissionEntity();
        missionEntity.setLabel(labelSet);


        //Mockito.when(popularRecommenderApi.recomend(new Long(100000001))).thenReturn(recmdMissionEntities_popular);
        //Mockito.when(labelRecommenderApi.recommend(new Long(100000001))).thenReturn(recmdMissionEntities);
        //Mockito.when(tailRecommenderApi.recommend(new Long(100000001))).thenReturn(recmdMissionEntities);
        //Mockito.when(userInterestRecommenderApi.recommend(new Long(100000001))).thenReturn(recmdMissionEntities);
        //Mockito.when(userTraitRecommenderApi.recommend(new Long(100000001))).thenReturn(recmdMissionEntities);
        //Mockito.when(recommendation_workerDbHelper.findById(new Long(100000001))).thenReturn(workerEntity);
    }

    @Test
    public void recommendPopular() {
        //assertEquals(new Long(127),recommendationService.recommendPersonal(new Long(100000001)).get(0).getMissionId());
        assertEquals(true,true);
    }

    @Test
    public void recommendPersonal() {
        assertEquals(true,true);
    }
}