package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.logentity.LogEntity;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.missionentity.MissionLabelEntity;
import business.common.entity.requesterentity.RequesterEntity;
import business.common.entity.tagentity.TagEntity;
import business.log.service.LogService;
import business.mission.helper.Mission_LabelDbHelper;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_RequesterDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.missionmanager.service.BuildMissionService;
import business.mission.picturesmanager.service.UploadPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class BuildMissionImpl implements BuildMissionService {

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_LabelDbHelper mission_labelDbHelper;

    @Autowired
    private UploadPicturesService uploadPicturesService;

    @Autowired
    private LogService logService;

    @Autowired
    private Mission_RequesterDbHelper missionRequesterDbHelper;

    @Autowired
    private Mission_TagDbHelper tagDbHelper;

    @Override
    public boolean buildMission(Long requesterId, String title, Set<Integer> label, String description,
                                String type, String[] pictures, int perPicPoints) throws Exception {

//        String missionId= missionDbHelper.getId();
//        Long missionId = new Long(-1);

        MissionEntity missionEntity_temp=new MissionEntity();
        Long missionId= missionMissionDbHelper.add(missionEntity_temp);
        Long[] picturesId=uploadPicturesService.uploadPictures(pictures,missionId);

        //update RequesterEntity
        /*
        include: releasedMissionsList(String[]), releasedMissionsNum+1, PaidPoints
         */
        RequesterEntity requesterEntity= missionRequesterDbHelper.findById(requesterId);
        Long[] releasedMissionsList=requesterEntity.getReleasedMissionsList();
        releasedMissionsList[requesterEntity.getReleasedMissionsNum()]=new Long(missionId);

        requesterEntity.setReleasedMissionsList(releasedMissionsList);
        requesterEntity.setReleasedMissionsNum(requesterEntity.getReleasedMissionsNum()+1);
        requesterEntity.setPaidPoints(requesterEntity.getPaidPoints()+perPicPoints*picturesId.length);
        missionRequesterDbHelper.update(requesterEntity);

        //set up missioninfodomain
        /*
        include: MissionEntity(missionId,requesterId,title,
                description,type,totalPoints,
                planToTagQueue(full),assignedTagSet(null),pic2TagMap(full),planToCheckQueue(null),
                assignedCheckMap(full),picturesId.length,picturesId.length,0(picturefinishnumber),tagPrice,checkPrice,
                participants(String[] participants={""}));

         */
        Integer checkPrice=perPicPoints/7;
        Integer tagPrice=checkPrice*2;

        Queue<Long> planToTagQueue=new LinkedList<>();
        for(Long pictureId:picturesId){
            planToTagQueue.add(pictureId);
        }

        Set<Long> assignedTagSet=new HashSet<>();

        Map<Long,Long> pic2TagMap=new HashMap<>();
        for(int i=0;i<picturesId.length;i++){
            TagEntity tagEntity=new TagEntity();
            Long tagId=tagDbHelper.add(tagEntity);
            pic2TagMap.put(picturesId[i],tagId);
        }

        Queue<Long> planToCheckQueue=new LinkedList<>();

        Map<Long,Integer> assignedCheckMap=new HashMap<>();
        for(int i=0;i<picturesId.length;i++){
            assignedCheckMap.put(picturesId[i],0);
        }

        Long[] participants=new Long[0];
        MissionEntity missionEntity =new MissionEntity(requesterId,label,title,
                description,type,perPicPoints*pictures.length,
                planToTagQueue,0.0,assignedTagSet,pic2TagMap,planToCheckQueue,assignedCheckMap,
                picturesId.length,picturesId.length,0,tagPrice,checkPrice,participants);
        missionEntity.setMissionId(missionId);

        missionMissionDbHelper.update(missionEntity);

        LogEntity logEntity=new LogEntity(requesterId,"requester",missionId,"新建");
        logService.addLog(logEntity);

        //前往missionLabelEntity中添加相应任务
        Integer[] labels=label.toArray(new Integer[label.size()]);
        for(int i=0;i<label.size();i++){
            MissionLabelEntity missionLabelEntity=mission_labelDbHelper.findByLabelId(labels[i].longValue());
            Set<Long> set=missionLabelEntity.getMissionIdList();
            set.add(missionId);
            missionLabelEntity.setMissionIdList(set);
            mission_labelDbHelper.update(missionLabelEntity);
        }
        return true;
    }
}