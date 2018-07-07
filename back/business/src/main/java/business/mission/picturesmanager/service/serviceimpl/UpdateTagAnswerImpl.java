package business.mission.picturesmanager.service.serviceimpl;

import business.common.entity.tagentity.Tag;
import business.common.entity.tagentity.TagEntity;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.picturesmanager.service.UpdateTagAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateTagAnswerImpl implements UpdateTagAnswerService {

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Override
    public boolean updateTagAnswer(Long missionId, Long authorId, Long pictureId, String mark, String tag,Integer tagTime){
        //String missionId=pictureId.split("_")[0];
//        Long missionId = new Long(-1);

        Map<Long,Long> pic2TagMap= missionMissionDbHelper.findById(missionId).getPic2TagMap();

        Long tagId=pic2TagMap.get(pictureId);

        TagEntity tagEntity= missionTagDbHelper.findById(tagId);

        Tag tag1=new Tag(authorId,mark,tag,tagTime);
        tagEntity.setTag(tag1);

        missionTagDbHelper.update(tagEntity);

        return true;
    }
}
