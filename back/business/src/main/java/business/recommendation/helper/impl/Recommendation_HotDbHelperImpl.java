package business.recommendation.helper.impl;

import business.common.entity.missionentity.HotMissionEntity;
import business.common.repository.HotMissionRepository;
import business.mission.helper.Mission_HotDbHelper;
import business.recommendation.helper.Recommendation_HotDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class Recommendation_HotDbHelperImpl implements Recommendation_HotDbHelper {
    @Autowired
    private HotMissionRepository hotMissionRepository;

    @Override
    public HotMissionEntity[] getAll() {
        return hotMissionRepository.getAll();
    }

    @Override
    public boolean updateAll(HotMissionEntity[] hotMissionEntities) {
        for(int i=0;i<hotMissionEntities.length;i++)
        hotMissionRepository.save(hotMissionEntities[i]);
        return true;
    }
}
