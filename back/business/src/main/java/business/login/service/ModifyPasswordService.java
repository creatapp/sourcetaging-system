package business.login.service;

import org.springframework.stereotype.Component;

@Component
public interface ModifyPasswordService {

    boolean modifyPassword(Long id,
                           String oldPassword,
                           String newPassword);

}
