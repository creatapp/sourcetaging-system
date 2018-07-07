package business.login.auth.service;

import business.login.auth.domain.UserToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public interface AuthService {

    Long decodeCookiesForId(Cookie[] cookies);

    String encodeCookies(UserToken userToken);
}
