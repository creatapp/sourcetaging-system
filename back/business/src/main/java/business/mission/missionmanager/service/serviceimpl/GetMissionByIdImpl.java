package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_PictureDbHelper;
import business.mission.missionmanager.service.GetMissionByIdService;
import business.mission.missionmanager.vo.MissionInfoReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

@Transactional
@Service
public class GetMissionByIdImpl  implements GetMissionByIdService{
    @Autowired
    private Mission_MissionDbHelper mission_missionDbHelper;

    @Autowired
    private Mission_PictureDbHelper mission_pictureDbHelper;
    
    @Override
    public MissionInfoReturnVO getMissionInfo(Long missionId) {
        MissionEntity missionEntity=mission_missionDbHelper.findById(missionId);
        String[] samplePics=new String[3];
//        Queue<Long> plan2TagQueue=missionEntity.getPlanToTagQueue();
//        Queue<Long> plan2CheckQueue=missionEntity.getPlanToCheckQueue();
//        for(int i=0;i<3;i++){
//            if(!plan2TagQueue.isEmpty()){
//                samplePics[i]=mission_pictureDbHelper.getPicById(plan2TagQueue.poll()).getPictureBits();
//            }else{
//                samplePics[i]=mission_pictureDbHelper.getPicById(plan2CheckQueue.poll()).getPictureBits();
//            }
//        }

        int i=0;
        Map<Long,Integer> assignedCheckMap=missionEntity.getAssignedCheckMap();
        Set<Long> keySet=assignedCheckMap.keySet();
        for(Iterator<Long> iterator = keySet.iterator(); iterator.hasNext()&&i<3;){
            Long key = iterator.next();
            samplePics[i]=mission_pictureDbHelper.getPicById(key).getPictureBits();
            i++;
        }
        MissionInfoReturnVO missionInfoReturnVO=new MissionInfoReturnVO(missionEntity,samplePics);
        return missionInfoReturnVO;
    }
}
