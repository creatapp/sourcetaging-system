package business.user.helper;

import business.common.entity.requesterentity.RequesterEntity;
import org.springframework.stereotype.Component;

@Component
public interface User_RequesterDbHelper {

    void add(RequesterEntity requesterEntity);

    RequesterEntity findById(Long id);

    void update(RequesterEntity requesterEntity);

    RequesterEntity[] findAll();

    boolean exists(Long requesterId);
}
