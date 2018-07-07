package business.recommendation.helper.impl;

import business.common.entity.missionentity.MissionEntity;
import business.common.exception.LogicException;
import business.common.repository.MissionRepository;
import business.recommendation.helper.Recommendation_MissionDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class Recommendation_MissionDbHelperImpl implements Recommendation_MissionDbHelper {

    @Autowired
    private MissionRepository missionRepository;

    @Override
    public boolean update(MissionEntity missionEntity){
        missionRepository.save(missionEntity);
        return true;
    }

    @Override
    public MissionEntity findById(long id){
        Optional<MissionEntity> optionalMissionEntity=  missionRepository.findById(id);
        if(optionalMissionEntity.isPresent())return optionalMissionEntity.get();
        else throw new LogicException("没有找到任务");
    }

    @Override
    public Long add(MissionEntity missionEntity) {
        return missionRepository.save(missionEntity).getMissionId();
    }

    @Override
    public MissionEntity[] listAllMissions() {
        return missionRepository.listAllMissions();
    }

    @Override
    public MissionEntity[] listUnfinishedMissions() {
        MissionEntity[] missionEntities = missionRepository.listUnfinishedMissions();
        return missionEntities;
    }

}
