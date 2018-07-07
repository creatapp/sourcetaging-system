package business.mission.picturesmanager.service.serviceimpl;

import business.common.entity.tagentity.Check;
import business.common.entity.tagentity.TagEntity;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.picturesmanager.service.UpdateCheckAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateCheckAnswerImpl implements UpdateCheckAnswerService {

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Override
    public boolean updateCheckAnswer(Long missionId, Long authorId, Long checkerId, Long pictureId,String mark,String tag,boolean checkAnswer,Integer checkTime){
        //String missionId=pictureId.split("_")[0];
//        Long missionId = new Long(-1);

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
        tagEntity.setChecks(checks);

        missionTagDbHelper.update(tagEntity);
        return true;
    }
}