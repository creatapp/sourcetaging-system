package business.user.usermanager.service;

import business.common.returnmodel.VO;
import org.springframework.stereotype.Component;

@Component
public interface RequesterInfoService {

    VO getRequesterInfo(Long id);

    VO[] getAllRequester();
}
