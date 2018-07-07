package business.mission.missionmanager.service;

import business.mission.missionmanager.vo.NewMissionInfoReturnVO;
import org.springframework.stereotype.Component;

@Component
public interface ListNewMissionListService {

    NewMissionInfoReturnVO[] listNewMissionList(Long workerId) throws Exception;
}
