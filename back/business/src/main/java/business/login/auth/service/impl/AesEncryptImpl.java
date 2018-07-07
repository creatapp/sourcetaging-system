package business.login.auth.service.impl;

import business.login.auth.service.AesEncryptService;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


@Service
public class AesEncryptImpl implements AesEncryptService {
    /*
        这个集合包含了提供的用于加密解密的方法集合，默认提供128位aes加密，key是"nju_SECIII"
        对外仅提供base64加密的密文
        算法我也不懂，参考https://blog.csdn.net/xuemengrui12/article/details/54583215
        被注释掉的是因为可能导致转string出现异常
     */

    private static final String key = "nju_SECIII";

    /*
     * @ param1 : content   加密内容
     *
     * @ return : 加密后的字节串
     *
     * @ function : aes加密
     */
    public static byte[] encrypt(String content) throws Exception {
        SecretKeySpec skeySpec = getKey(key);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return  encrypted;
    }


    /*
     * @ param : content    解密的密文
     *
     * @ return : 明文
     *
     * @ function : aes解密
     */
    public static String decrypt(byte[] content) throws Exception {
        SecretKeySpec skeySpec = getKey(key);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(content);
        String originalString = new String(original);
        return originalString;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }


    /*
     * @ param : content 待加密的已经aes加密的字符
     *
     * @ return : base64加密后的字符串
     *
     * @ function : base64加密
     */
    public static String base64Encode(byte[] content){
        return new BASE64Encoder().encode(content);
    }


    /*
     * @ param : base64Code base64加密字符串
     *
     * @ return : aes密文字符数组
     *
     * @ function : base64解密
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return base64Code.isEmpty() ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    @Override
    public String aesEncrypt(String content) throws Exception {
        return base64Encode(encrypt(content));
    }

    @Override
    public String aesDecrypt(String base64Code) throws Exception {
        return decrypt(base64Decode(base64Code));
    }
}

