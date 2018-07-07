package business.login.service.serviceimpl;

import business.common.entity.userentity.UserEntity;
import business.login.auth.domain.UserToken;
import business.login.auth.service.AesEncryptService;
import business.login.auth.service.AuthService;
import business.login.helper.Login_UserDbHelper;
import business.login.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private Login_UserDbHelper loginUserDbHelper;

    @Autowired
    private AuthService authService;

    @Autowired
    private AesEncryptService aesEncryptService;

    @Override
    public String signIn(String type,
                         Long id,
                         String password,
                         HttpServletResponse response) {

        if(type.equals("admin")){
            if(id==100000000 && password.equals("seciiii")){
                return "";
            }else{
                return "typeError";
            }
        }

        UserEntity userEntity = loginUserDbHelper.findById(id);
        if(userEntity.getId() < 0)return "typeError";
        if(userEntity.getType().toLowerCase().equals(type)){

            //密码经过加密，需要解密进行匹配
            String passAfterDecode;
            try {
                passAfterDecode = aesEncryptService.aesDecrypt(userEntity.getPassword());
            } catch (Exception e) {
                return "typeError";
            }

            if(!passAfterDecode.equals(password)){
                return "typeError";
            }

//            设置cookies
            UserToken userToken = new UserToken(id, type,password);
            String base64Code = authService.encodeCookies(userToken);
            Cookie cookie = new Cookie("SECIII",base64Code);
            cookie.setPath("/");
            cookie.setMaxAge(7200);//2 hours
            response.addCookie(cookie);

            return userEntity.getNickName();
        }else{
            return "typeError";
        }
    }

}
