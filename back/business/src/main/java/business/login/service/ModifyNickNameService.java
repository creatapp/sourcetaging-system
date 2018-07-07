package business.login.service;

import org.springframework.stereotype.Component;

@Component
public interface ModifyNickNameService {

    boolean modifyNickName(String type,
                           String newNickName,
                           Long id);
}
