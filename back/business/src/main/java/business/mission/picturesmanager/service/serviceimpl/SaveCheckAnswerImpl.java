package business.mission.picturesmanager.service.serviceimpl;

import business.common.entity.tagentity.Check;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.picturesmanager.service.ChangeMissionService;
import business.mission.picturesmanager.service.SaveCheckAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
@Transactional
@Service
public class SaveCheckAnswerImpl implements SaveCheckAnswerService {

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;

    @Autowired
    private ChangeMissionService changeMissionService;

    @Override
    public String saveCheckAnswer(Long missionId, Long authorId, Long checkerId, Long pictureId,boolean checkAnswer, Integer checkTime) {

//        Long missionId=pictureId.split("_")[0];
//        Long missionId = new Long(0);

        Map<Long,Long> pic2TagMap= missionMissionDbHelper.findById(missionId).getPic2TagMap();

        Long tagId=pic2TagMap.get(pictureId);

        TagEntity tagEntity= missionTagDbHelper.findById(tagId);

        Check[] checks=tagEntity.getChecks();
        for(int i=0;i<checks.length;i++){
            if(checks[i].getWorkerId().equals(checkerId)){
                checks[i].setJudge(checkAnswer);
                checks[i].setCheckTime(checkTime);
                break;
            }
        }

        boolean hasFinished=true;
        //judge whether has finished
        for(int i=0;i<checks.length;i++){
            if(checks[i].getWorkerId().equals("")){
                hasFinished=false;
                break;
            }
        }

        //更新tagdomain
        /*
        include: HasFinished, Checks,
         */
        tagEntity.setHasFinished(hasFinished);
        tagEntity.setChecks(checks);
        missionTagDbHelper.update(tagEntity);

        //update workerinfodomain
        /*
        include: doingmissiondomain(DoneNum+1, Tag_index+1)
         */
        WorkerEntity workerEntity= missionWorkerDbHelper.findById(checkerId);
        DoingMissionEntity[] doingMissionEntities=workerEntity.getDoingMissionEntities();
        for(int m=0;m<doingMissionEntities.length;m++){
            if(doingMissionEntities[m].getMissionId() == (missionId)){
                doingMissionEntities[m].setDoneNum(doingMissionEntities[m].getDoneNum()+1);
                doingMissionEntities[m].setTag_index(doingMissionEntities[m].getTag_index()+1);
                workerEntity.setDoingMissionEntities(doingMissionEntities);
                missionWorkerDbHelper.update(workerEntity);

                //judge whether has finished
                if(doingMissionEntities[m].getDoneNum()>=doingMissionEntities[m].getTotalNum()){
                    changeMissionService.changeMission(missionId,checkerId,"check");
                    return "finished";
                }
                break;
            }
        }

        return "success";
    }
}
