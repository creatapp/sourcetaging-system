package business.login.helper.impl;

import business.common.entity.userentity.UserEntity;
import business.common.exception.LogicException;
import business.common.repository.UserRepository;
import business.login.helper.Login_UserDbHelper;
import business.login.po.usercommoninfo.UserCommonInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Login_UserDbHelperImpl implements Login_UserDbHelper {

    @Autowired
    private UserRepository userRepository;

    @Override
    public long add(UserCommonInfoPO userCommonInfoPO) {
        return userRepository.save(new UserEntity(userCommonInfoPO)).getId();
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent())return userEntityOptional.get();
        else throw new LogicException("为找到该用户");
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
