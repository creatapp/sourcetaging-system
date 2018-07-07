package business.login.helper;

import business.common.entity.requesterentity.RequesterEntity;
import org.springframework.stereotype.Component;

@Component
public interface Login_RequesterDbHelper {

    void add(RequesterEntity requesterEntity);
}
