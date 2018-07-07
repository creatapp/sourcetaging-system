package business.user.usermanager.service.impl;

import business.common.entity.userentity.UserEntity;
import business.user.helper.User_UserDbHelper;
import business.user.usermanager.service.UserInfoUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoUpdateServiceImpl implements UserInfoUpdateService {

    @Autowired
    User_UserDbHelper userUserDbHelper;

    @Override
    public boolean changeNickName(Long id, String newNickName) {

        UserEntity userEntity = userUserDbHelper.findById(id);

        userEntity.setNickName(newNickName);
        userUserDbHelper.update(userEntity);

        return true;
    }

    @Override
    public boolean changeEmail(Long id, String newEmail) {

        UserEntity userEntity = userUserDbHelper.findById(id);

        userEntity.setEmail(newEmail);
        userUserDbHelper.update(userEntity);
        return true;
    }
}