package business.mission.helper;

import business.common.entity.requesterentity.RequesterEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mission_RequesterDbHelper {

    void add(RequesterEntity requesterEntity);

    RequesterEntity findById(Long id);

    void update(RequesterEntity requesterEntity);
}
