package business.login.service;

import org.springframework.stereotype.Component;

@Component
public interface FetchPasswordService {

    boolean fetchPassword(String email);
}
