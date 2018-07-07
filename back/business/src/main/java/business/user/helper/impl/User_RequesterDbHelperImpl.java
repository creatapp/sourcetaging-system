package business.user.helper.impl;

import business.common.entity.requesterentity.RequesterEntity;
import business.common.repository.RequesterRepository;
import business.user.helper.User_RequesterDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class User_RequesterDbHelperImpl implements User_RequesterDbHelper {

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

    @Override
    public RequesterEntity[] findAll() {
        Iterable<RequesterEntity> requesterEntityIterable = requesterRepository.findAll();
        Iterator<RequesterEntity> requesterEntityIterator = requesterEntityIterable.iterator();

        ArrayList<RequesterEntity> requesterEntities = new ArrayList<>();
        while (requesterEntityIterator.hasNext()){
            requesterEntities.add(requesterEntityIterator.next());
        }
        RequesterEntity[] requesterEntities1=new RequesterEntity[requesterEntities.size()];
        for(int i=0;i<requesterEntities.size();i++){
            requesterEntities1[i]=requesterEntities.get(i);
        }
        return requesterEntities1;
    }

    @Override
    public boolean exists(Long requesterId) {
        return requesterRepository.existsById(requesterId);
    }
}
