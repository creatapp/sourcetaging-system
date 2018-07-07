package business.recommendation.service;

/*本类执行离线推荐计算*/

import business.common.entity.missionentity.HotMissionEntity;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.entity.workerentity.SimWorkerEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.datahelper.UserDataHelper;
import business.recommendation.core.recommender.UserSimilarityRecommender;
import business.recommendation.helper.Recommendation_HotDbHelper;
import business.recommendation.helper.Recommendation_MissionDbHelper;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Component
public class RecommendationOfflineSchedule {

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Autowired
    private UserDataHelper userDataHelper;

    @Autowired
    private UserSimilarityRecommender userSimilarityRecommender;

    @Autowired
    private Recommendation_MissionDbHelper recommendation_missionDbHelper;

    @Autowired
    private Recommendation_HotDbHelper recommendation_hotDbHelper;

    @Scheduled(fixedRate = 1000 * 60 * 5) //数据量不大先每隔一个小时计算一次
    public void doSchedule() {
        /*
        1、计算相似用户
        2、更新热度列表
        */

        userSimilarityRecommender.recommend(userDataHelper.getWorkerInterestData());
        userSimilarityRecommender.recommend(userDataHelper.getWorkerTraitData());

        ArrayList<RecmdMissionEntity> recmdMissionEntities=new ArrayList<>();

        MissionEntity[] missionEntities=recommendation_missionDbHelper.listUnfinishedMissions();
        //热度
        Map<Long,Integer> hoter=new HashMap<>();   //Long:missionId,Integer:total of participants

        Double missionTotal=(double)missionEntities.length;
        for(MissionEntity missionEntity: missionEntities){
            Long missionId=missionEntity.getMissionId();
            hoter.put(missionId,missionEntity.getParticipants().length);
        }

        int rank=1;
        int total=0;
        int judge=0;
        Double average=0.0;
        //排序并注入
        HotMissionEntity[] hotMissionEntities=new HotMissionEntity[missionEntities.length];
        List<Map.Entry<Long,Integer>> list = new ArrayList<Map.Entry<Long,Integer>>(hoter.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Long,Integer>>() {
            //升序排序
            public int compare(Map.Entry<Long, Integer> o1,
                               Map.Entry<Long, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });

        for(Map.Entry<Long,Integer> mapping: list){
            total=total+mapping.getValue();
        }

        if(total==0){
            judge=1;
        }
        else{
            average=(double)total/hotMissionEntities.length;
        }

        Set<Long> keySet=hoter.keySet();
        for(Iterator<Long> iterator = keySet.iterator();iterator.hasNext();){
            Long key = iterator.next();
            Integer value = hoter.get(key);

            if(judge==1){
                hotMissionEntities[rank-1]=new HotMissionEntity(key,0.5);
            }
            else{
                double point=0.3;
                if(rank<9){
                    point=1-(rank-1)*0.1;
                }
                hotMissionEntities[rank-1]=new HotMissionEntity(key,point);
            }
            hotMissionEntities[rank-1].setRank(new Long((long)rank));
            rank++;
        }

        recommendation_hotDbHelper.updateAll(hotMissionEntities);

//        WorkerEntity[] workerEntities=recommendation_workerDbHelper.findAll();
//        for(WorkerEntity workerEntity_base: workerEntities){
//            ArrayList<SimWorkerEntity> recmdTraitSimWorker=new ArrayList<>();
//            ArrayList<SimWorkerEntity> recmdInterestSimWorker=new ArrayList<>();
//            for(WorkerEntity workerEntity_compare: workerEntities){
//
//            }
//        }


    }
}
