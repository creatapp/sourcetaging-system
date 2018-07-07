package business.login.service.serviceimpl;

import business.common.entity.userentity.UserEntity;
import business.login.helper.Login_UserDbHelper;
import business.login.service.ModifyNickNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyNickNameServiceImpl implements ModifyNickNameService {

    @Autowired
    private Login_UserDbHelper loginUserDbHelper;

    @Override
    public boolean modifyNickName(String type,
                                  String newNickName,
                                  Long id) {
        UserEntity userEntity = loginUserDbHelper.findById(id);
        if(userEntity == null)return false;
        else{
            userEntity.setNickName(newNickName);
            loginUserDbHelper.update(userEntity);

            return true;
        }
    }
}
