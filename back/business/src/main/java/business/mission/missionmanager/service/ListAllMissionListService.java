package business.mission.missionmanager.service;

import business.mission.missionmanager.vo.MissionInfoReturnVO;
import org.springframework.stereotype.Component;

@Component
public interface ListAllMissionListService {

    MissionInfoReturnVO[] ListAllMissionList() throws Exception;
}
