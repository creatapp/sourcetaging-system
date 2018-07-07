package business.mission.helper;

import business.common.entity.userentity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mission_UserDbHelper {

    UserEntity findById(Long id);

    void update(UserEntity userEntity);

}
