package business.user.workermanager.service;

import business.common.returnmodel.VO;
import org.springframework.stereotype.Component;

@Component
public interface DoneListService {

    VO[] listDoneList(Long workerId);
}
