package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.HotMissionEntity;
import business.mission.helper.Mission_HotDbHelper;
import business.mission.missionmanager.service.HotMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotMissionImpl implements HotMissionService{
    @Autowired
    private Mission_HotDbHelper mission_hotDbHelper;
    @Override
    public HotMissionEntity[] getAll() {
        return mission_hotDbHelper.getAll();
    }

    @Override
    public boolean updateAll(HotMissionEntity[] hotMissionEntities) {
        mission_hotDbHelper.updateAll(hotMissionEntities);
        return true;
    }
}
