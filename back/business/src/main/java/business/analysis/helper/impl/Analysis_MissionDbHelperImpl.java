package business.analysis.helper.impl;

import business.analysis.helper.Analysis_MissionDbHelper;
import business.common.entity.missionentity.MissionEntity;
import business.common.exception.LogicException;
import business.common.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Analysis_MissionDbHelperImpl implements Analysis_MissionDbHelper {

    @Autowired
    private MissionRepository missionRepository;

    public boolean udpate(MissionEntity missionEntity){
        missionRepository.save(missionEntity);
        return true;
    }

    public MissionEntity findById(long id){
        Optional<MissionEntity> optionalMissionEntity=  missionRepository.findById(id);
        if(optionalMissionEntity.isPresent())return optionalMissionEntity.get();
        else throw new LogicException("没有找到任务");
    }

}
