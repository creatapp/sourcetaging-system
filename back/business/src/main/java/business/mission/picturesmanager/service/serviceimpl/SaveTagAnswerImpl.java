package business.mission.picturesmanager.service.serviceimpl;

import business.common.entity.logentity.LogEntity;
import business.common.entity.tagentity.Tag;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.log.service.LogService;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.picturesmanager.service.ChangeMissionService;
import business.mission.picturesmanager.service.SaveTagAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Transactional
@Service
public class SaveTagAnswerImpl implements SaveTagAnswerService {

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private LogService logService;

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;

    @Autowired
    private ChangeMissionService changeMissionService;

    @Override
    public String saveTagAnswer(Long missionId, Long authorId, Long pictureId, String mark, String tag,Long picTime, Integer tagTime) {

        //String missionId=pictureId.split("_")[0];
//        Long missionId = new Long(-1);

        Map<Long,Long> pic2TagMap= missionMissionDbHelper.findById(missionId).getPic2TagMap();

        Long tagId=pic2TagMap.get(pictureId);

        Tag tag1=new Tag(authorId,mark,tag,tagTime);

        //update tagdomain
        /*
        include: tag
         */
        TagEntity tagEntity= missionTagDbHelper.findById(tagId);
//        if(!tagDomain.getWorkerId().equals(authorId)){
//            throw new MissionException("无该任务");
//        }
        tagEntity.setTag(tag1);
        tagEntity.setPictureTime(picTime);
        missionTagDbHelper.update(tagEntity);

        //update workerinfodomain
        /*
        include: doingmissiondomain(DoneNum+1, Tag_index+1)
         */
        WorkerEntity workerEntity= missionWorkerDbHelper.findById(authorId);
        DoingMissionEntity[] doingMissionEntities=workerEntity.getDoingMissionEntities();
        int i=0;
        for(;i<doingMissionEntities.length;i++){
            if(doingMissionEntities[i].getMissionId() == (missionId)){
                doingMissionEntities[i].setDoneNum(doingMissionEntities[i].getDoneNum()+1);
                doingMissionEntities[i].setTag_index(doingMissionEntities[i].getTag_index()+1);
                workerEntity.setDoingMissionEntities(doingMissionEntities);
                missionWorkerDbHelper.update(workerEntity);
                break;
            }

        }
        //judge whether has finished
        if(doingMissionEntities[i].getDoneNum()>=doingMissionEntities[i].getTotalNum()){
            changeMissionService.changeMission(missionId,authorId,"tag");
            LogEntity logEntity=new LogEntity(authorId,"worker",missionId,"提交");
            logService.addLog(logEntity);
            return "finished";
        }

        return "success";
    }
}