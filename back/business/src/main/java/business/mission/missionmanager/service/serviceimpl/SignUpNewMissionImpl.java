package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.logentity.LogEntity;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.log.service.LogService;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.missionmanager.service.AssignMissionService;
import business.mission.missionmanager.service.SignUpNewMissionService;
import business.recommendation.service.RecommendationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class SignUpNewMissionImpl implements SignUpNewMissionService {

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private RecommendationFeedbackService recommendationFeedbackService;

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;

    @Autowired
    private LogService logService;

    @Autowired
    private AssignMissionService assignMissionService;

    @Override
    public String signUpMission(Long workerId, Long missionId,String kind) throws Exception {

        WorkerEntity workerEntity= missionWorkerDbHelper.findById(workerId);
        DoingMissionEntity[] doingMissionEntities =workerEntity.getDoingMissionEntities();

        //compare whether having this mission
        for(int i=0;i<workerEntity.getDoingMissionsNum();i++){
            if(doingMissionEntities[i].getMissionId() == (missionId)){
                return "任务已经注册到该工人名下";
            }
        }

        //if not, judge whether the number of doingMissionsNum if full
        if(workerEntity.getDoingMissionsNum()>=4){
            return "可进行任务已满";
        }

        //if not, judge whether the worker has done this mission
        MissionEntity missionEntity = missionMissionDbHelper.findById(missionId);
        Long[] participants= missionEntity.getParticipants();

        //compare the participant in participants
        for(Long participant:participants){
            if(participant.equals(workerId)){
                return "已进行过此任务，无法再次进行此任务";
            }
        }

        //if not, judge whether the number of pictures
        if(kind.equals("check")){
            Queue<Long> planToCheckQueue= missionEntity.getPlanToCheckQueue();
            if(!planToCheckQueue.isEmpty()){
                assignMissionService.assignMission(workerId,kind,missionId, missionEntity.getType());
                LogEntity logEntity=new LogEntity(workerId,"worker",missionId,"领取");
                logService.addLog(logEntity);
                recommendationFeedbackService.adjust(workerId,missionId,0);
                return "success";
            }
            else{
                return "任务已分配完";
            }
        }
        //tag
        else{
            Queue<Long> planToTagQueue= missionEntity.getPlanToTagQueue();
            if(!planToTagQueue.isEmpty()){
                assignMissionService.assignMission(workerId,kind,missionId, missionEntity.getType());
                LogEntity logEntity=new LogEntity(workerId,"worker",missionId,"领取");
                logService.addLog(logEntity);
                recommendationFeedbackService.adjust(workerId,missionId,0);
                return "success";
            }
            else{
                return "任务已分配完";
            }
        }

    }
}
