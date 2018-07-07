package business.mission.missionmanager.service;

import business.mission.missionmanager.vo.MissionInfoReturnVO;
import org.springframework.stereotype.Component;

@Component
public interface GetRequesterMissionsService {

    MissionInfoReturnVO[] getRequesterMission(Long requesterId) throws Exception;
}
