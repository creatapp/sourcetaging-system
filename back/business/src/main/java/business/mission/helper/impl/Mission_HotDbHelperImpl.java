package business.mission.helper.impl;

import business.common.entity.missionentity.HotMissionEntity;
import business.common.repository.HotMissionRepository;
import business.mission.helper.Mission_HotDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mission_HotDbHelperImpl implements Mission_HotDbHelper {
    @Autowired
    private HotMissionRepository hotMissionRepository;

    @Override
    public HotMissionEntity[] getAll() {
        return hotMissionRepository.getAll();
    }

    @Override
    public boolean updateAll(HotMissionEntity[] hotMissionEntities) {
        hotMissionRepository.deleteAll();
        for(int i=0;i<hotMissionEntities.length;i++)
        hotMissionRepository.save(hotMissionEntities[i]);
        return true;
    }
}
