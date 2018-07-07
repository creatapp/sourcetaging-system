package business.user.usermanager.service;

import business.common.returnmodel.VO;
import org.springframework.stereotype.Component;

@Component
public interface WorkerInfoService {

    VO getUserInfo(Long userId);

    VO getWorkerData(Long workerId)
;
    VO[] getAllWorkers();

}
