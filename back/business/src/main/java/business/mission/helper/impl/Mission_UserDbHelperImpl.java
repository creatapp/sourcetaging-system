package business.mission.helper.impl;

import business.common.entity.userentity.UserEntity;
import business.common.exception.LogicException;
import business.common.repository.UserRepository;
import business.mission.helper.Mission_UserDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Mission_UserDbHelperImpl implements Mission_UserDbHelper {

    @Autowired
    private UserRepository userRepository;

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
