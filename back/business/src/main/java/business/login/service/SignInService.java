package business.login.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public interface SignInService {

    String signIn(String type,
                  Long id,
                  String password,
                  HttpServletResponse response);

}
