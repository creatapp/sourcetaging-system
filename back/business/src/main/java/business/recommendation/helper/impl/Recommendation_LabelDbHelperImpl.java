package business.recommendation.helper.impl;

import business.common.entity.missionentity.MissionLabelEntity;
import business.common.repository.MissionLabelRepository;
import business.recommendation.helper.Recommendation_LabelDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
public class Recommendation_LabelDbHelperImpl implements Recommendation_LabelDbHelper {
    @Autowired
    private MissionLabelRepository missionLabelRepository;

    @Override
    public boolean update(MissionLabelEntity missionLabelEntity) {
        missionLabelRepository.save(missionLabelEntity);
        return true;
    }

    @Override
    public MissionLabelEntity findByLabelId(Long labelId) {
        if(missionLabelRepository.existsById(labelId)){
            MissionLabelEntity missionLabelEntity=missionLabelRepository.findById(labelId).get();
            return missionLabelEntity;
        }else{
            return new MissionLabelEntity(labelId,new HashSet<Long>());
        }
    }
}
