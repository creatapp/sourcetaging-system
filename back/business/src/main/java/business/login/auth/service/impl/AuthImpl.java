package business.login.auth.service.impl;

import business.common.exception.LogicException;
import business.login.auth.domain.UserToken;
import business.login.auth.service.AesEncryptService;
import business.login.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class AuthImpl implements AuthService {

    @Autowired
    private AesEncryptService aesEncryptService;

    /*
     * @ param : cookies    获得的cookies
     *
     * @ return : UserToken对象
     *
     * @ function : 将cookies进行解码，然后将信息组装为UserToken
     */
    private UserToken decodeCookies(String cookies) {

        //先进行解码
        String info;
        try{
            info = aesEncryptService.aesDecrypt(cookies);
        }catch (Exception   e){
            throw new LogicException("解密部件出现异常");
        }

        //约定如下明文
        // id___type___password
        String[] infoo = info.split("___");
        if(infoo.length < 3){
//            throw new LogicException("cookies已经损坏");
            return null;
        }

        UserToken userToken = new UserToken(new Long(infoo[0]),infoo[1],infoo[2]);
        return userToken;
    }


    /*
     * @ param : userToken请求生成加密的cookies
     *
     * @ return : aes密文
     *
     * @ function : aes加密后的base64密文
     */
    public String encodeCookies(UserToken userToken) {

        if(userToken == null){
            return  "";
        }

        String info = userToken.getId() + "___" + userToken.getType() + "___" + userToken.getPassword();

        String code;
        try{
            code = aesEncryptService.aesEncrypt(info);
        }catch (Exception e){
            throw new LogicException("加密部件出现异常");
        }
        return code;
    }

    public Long decodeCookiesForId(Cookie[] cookies){
        Long id=0L;
        for (int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("SECIII")){
                id=this.decodeCookies(cookies[i].getValue()).getId();
                break;
            }
        }
        if(id==0L){
            throw new LogicException("读取不到cookie");
        }
        return id;
    }
}
