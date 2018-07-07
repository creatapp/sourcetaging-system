package business.login.service;

import org.springframework.stereotype.Component;

@Component
public interface SignUpService {

    String signUp(String type,
                  String password,
                  String nickName,
                  String email);
}
