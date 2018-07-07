package business.mission.picturesmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_PictureDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.picturesmanager.service.AskForPictureService;
import business.mission.picturesmanager.vo.PictureReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Transactional
@Service
public class AskForPictureImpl implements AskForPictureService {

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_PictureDbHelper mission_pictureDbHelper;

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;

    @Override
    public PictureReturnVO askForPicture(Long missionId, String kind, Long pictureId, Long workerId){


        //next picture
        if(pictureId==0){
            MissionEntity missionEntity= missionMissionDbHelper.findById(missionId);

            WorkerEntity workerEntity= missionWorkerDbHelper.findById(workerId);

            DoingMissionEntity[] doingMissionEntities=workerEntity.getDoingMissionEntities();
            Long nextTagId = new Long(-1);
            Integer left=0;
            for(DoingMissionEntity doingMissionEntity:doingMissionEntities){
                if(doingMissionEntity.getMissionId() == (missionId)){

                    if(doingMissionEntity.getTag_index()>=doingMissionEntity.getTotalNum()){
                        throw new LogicException("任务已完成");
                    }
                    //unfinished
                    nextTagId=doingMissionEntity.getDoingTagId()[doingMissionEntity.getTag_index()];
                    left=doingMissionEntity.getTotalNum()-doingMissionEntity.getDoneNum();
                    break;
                }
            }

            TagEntity tagEntityNext= missionTagDbHelper.findById(nextTagId);
            PictureReturnVO pictureReturnVO=new PictureReturnVO();
            if(kind.equals("check")){
                //deal with pictureId
                String picData=mission_pictureDbHelper.getPicById(tagEntityNext.getPictureId()).getPictureBits();
                pictureReturnVO=new PictureReturnVO(tagEntityNext.getWorkerId(),tagEntityNext.getPictureId(),picData,tagEntityNext.getTag().getMark(),
                        tagEntityNext.getTag().getComment(),left);
            }
            else{
                //deal with pictureId
                String picData=mission_pictureDbHelper.getPicById(tagEntityNext.getPictureId()).getPictureBits();
                pictureReturnVO=new PictureReturnVO(new Long(-1),tagEntityNext.getPictureId(),picData,"","",left);
            }

            return pictureReturnVO;
        }
        //this picture
        else{
            MissionEntity missionEntity= missionMissionDbHelper.findById(missionId);

            Map<Long,Long> pic2TagMap=missionEntity.getPic2TagMap();

            Long tagId=pic2TagMap.get(pictureId);

            TagEntity tagEntity= missionTagDbHelper.findById(tagId);

            PictureReturnVO pictureReturnVO=new PictureReturnVO();

            //get left
            WorkerEntity workerEntity= missionWorkerDbHelper.findById(workerId);

            DoingMissionEntity[] doingMissionEntities=workerEntity.getDoingMissionEntities();
            Integer left=0;
            for(DoingMissionEntity doingMissionEntity:doingMissionEntities){
                if(doingMissionEntity.getMissionId() == (missionId)){

                    //unfinished
                    left=doingMissionEntity.getTotalNum()-doingMissionEntity.getDoneNum();
                    break;
                }
            }

            if(kind.equals("check")){
                //deal with pictureId
                String picData=mission_pictureDbHelper.getPicById(tagEntity.getPictureId()).getPictureBits();


                pictureReturnVO=new PictureReturnVO(tagEntity.getWorkerId(),pictureId,picData,tagEntity.getTag().getMark(),
                        tagEntity.getTag().getComment(),left);
            }
            else{
                //deal with pictureId
                String picData=mission_pictureDbHelper.getPicById(tagEntity.getPictureId()).getPictureBits();
                pictureReturnVO=new PictureReturnVO(new Long(-1),pictureId,picData,tagEntity.getTag().getMark(),
                        tagEntity.getTag().getComment(),left);
            }

            return pictureReturnVO;
        }
    }
}
