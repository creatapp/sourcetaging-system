package business.mission.helper.impl;

import business.common.entity.missionentity.MissionEntity;
import business.common.repository.MissionRepository;
import business.mission.helper.Mission_RecommendationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mission_RecommandationHelperImpl implements Mission_RecommendationHelper {
    @Autowired
    private MissionRepository missionRepository;

    @Override
    public MissionEntity[] recommendMissions(Long workerId) {
        return missionRepository.listUnfinishedMissions();
    }

//    @Autowired
//    private RecommendMissionsService recommendMissionsService;
//
}
