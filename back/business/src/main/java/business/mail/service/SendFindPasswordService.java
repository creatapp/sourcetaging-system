package business.mail.service;

import org.springframework.stereotype.Component;

@Component
public interface SendFindPasswordService {

    void sendFindPasswordMail(Long id, String password, String mail) throws  Exception;
}
