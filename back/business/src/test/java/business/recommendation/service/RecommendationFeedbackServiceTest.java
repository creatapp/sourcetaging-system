//package business.recommendation.service;
//
//import business.BusinessApplication;
//import business.common.entity.missionentity.MissionEntity;
//import business.common.entity.missionentity.RecmdMissionEntity;
//import business.common.entity.workerentity.RecmdWeighEntity;
//import business.common.entity.workerentity.WorkerEntity;
//import business.recommendation.helper.Recommendation_MissionDbHelper;
//import business.recommendation.helper.Recommendation_WorkerDbHelper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BusinessApplication.class)
//public class RecommendationFeedbackServiceTest {
//
//    @Autowired
//    @InjectMocks
//    private RecommendationFeedbackService recommendationFeedbackService;
//
//    @Mock
//    private Recommendation_WorkerDbHelper workerDbHelper;
//
//    @Mock
//    private Recommendation_MissionDbHelper missionDbHelper;
//
//    @Before
//    public void setUp() throws Exception {
//        //生成workerEntity
//        RecmdWeighEntity recmdWeighEntity=new RecmdWeighEntity(1.0,0.0,0.0,0.0,0.0,0.0);
//        List<RecmdMissionEntity> label_recmdMissionEntityList = new ArrayList<>();
//        List<RecmdMissionEntity> userInterest_recmdMissionEntityList = new ArrayList<>();
//        List<RecmdMissionEntity> userTrait_recmdMissionEntityList = new ArrayList<>();
//        List<Long> uninterestList = new ArrayList<>();
//
//        WorkerEntity workerEntity=new WorkerEntity();
//        workerEntity.setUserInterest_recmdMissionEntityList(userInterest_recmdMissionEntityList);
//        workerEntity.setUserTrait_recmdMissionEntityList(userTrait_recmdMissionEntityList);
//        workerEntity.setUninterestedList(uninterestList);
//        workerEntity.setLabel_recmdMissionEntityList(label_recmdMissionEntityList);
//        workerEntity.setRecmdWeighEntity(recmdWeighEntity);
//
//        //生成missionEntity
//        Set<Integer> labelSet=new HashSet<>();
//        labelSet.add(1);
//        labelSet.add(2);
//        MissionEntity missionEntity=new MissionEntity();
//        missionEntity.setLabel(labelSet);
//
//        Mockito.when(workerDbHelper.findById(new Long(100000001))).thenReturn(workerEntity);
//        Mockito.when(missionDbHelper.findById(new Long(125))).thenReturn(missionEntity);
//        Mockito.when(workerDbHelper.update(workerEntity)).thenReturn(true);
//
//    }
//
//    @Test
//    public void adjust() {
//
//        assertEquals(true,true);
//    }
//}