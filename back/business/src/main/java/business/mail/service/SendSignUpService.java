package business.mail.service;

import org.springframework.stereotype.Component;

@Component
public interface SendSignUpService {

    void sendSignUpMail(Long id, String password, String mail) throws Exception;
}
