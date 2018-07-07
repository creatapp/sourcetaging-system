package business.mission.helper.impl;

import business.common.entity.requesterentity.RequesterEntity;
import business.common.repository.RequesterRepository;
import business.mission.helper.Mission_RequesterDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Mission_RequesterDbHelperImpl implements Mission_RequesterDbHelper {

    @Autowired
    private RequesterRepository requesterRepository;

    @Override
    public void add(RequesterEntity requesterEntity) {
        requesterRepository.save(requesterEntity);
    }

    @Override
    public RequesterEntity findById(Long id) {
        Optional<RequesterEntity> requesterEntityOptional = requesterRepository.findById(id);
        if(requesterEntityOptional.isPresent())return requesterEntityOptional.get();
        else throw new LinkageError("未找到该发起者信息");
    }

    @Override
    public void update(RequesterEntity requesterEntity) {
        requesterRepository.save(requesterEntity);
    }
}
