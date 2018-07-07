package business.login.helper.impl;

import business.common.entity.requesterentity.RequesterEntity;
import business.common.repository.RequesterRepository;
import business.login.helper.Login_RequesterDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login_RequesterDbHelperImpl implements Login_RequesterDbHelper {

    @Autowired
    private RequesterRepository requesterRepository;

    @Override
    public void add(RequesterEntity requesterEntity) {
        requesterRepository.save(requesterEntity);
    }
}
