package business.login.helper;

import business.common.entity.userentity.UserEntity;
import business.login.po.usercommoninfo.UserCommonInfoPO;
import org.springframework.stereotype.Component;

@Component
public interface Login_UserDbHelper {

    //返回ID
    long add(UserCommonInfoPO userCommonInfoPO);

    UserEntity findById(Long id);

    void update(UserEntity userEntity);

}
