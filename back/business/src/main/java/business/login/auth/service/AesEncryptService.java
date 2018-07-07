package business.login.auth.service;

import org.springframework.stereotype.Component;

@Component
public interface AesEncryptService {

    /*
     * @ param : content    明文
     *
     * @ return : ase加密后的base64字符串
     *
     * @ function : aes128+base64加密
     */
    String aesEncrypt(String content) throws Exception;


    /*
     * @ param : base64Code  密文
     *
     * @ return : 解密后的原来信息
     *
     * @ function : 解密
     */
    String aesDecrypt(String base64Code) throws Exception;
}
