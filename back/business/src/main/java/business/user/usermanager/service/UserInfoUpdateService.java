package business.user.usermanager.service;

import org.springframework.stereotype.Component;

@Component
public interface UserInfoUpdateService {

    boolean changeNickName(Long id, String newNickName);

    boolean changeEmail(Long id, String newEmail);
}