package business.login.service.serviceimpl;

import business.common.entity.userentity.UserEntity;
import business.login.auth.service.AesEncryptService;
import business.login.helper.Login_UserDbHelper;
import business.login.service.ModifyPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyPasswordServiceImpl implements ModifyPasswordService {

    @Autowired
    private Login_UserDbHelper loginUserDbHelper;

    @Autowired
    private AesEncryptService aesEncryptService;

    @Override
    public boolean modifyPassword(Long id,
                                  String oldPassword,
                                  String newPassword) {
        UserEntity userEntity = loginUserDbHelper.findById(id);
        if(userEntity == null)return false;

        //先验证密码是否正确，然后再更改密码，需要解密和加密
        String oldPassAfterDecode = "";
        String newPassAfterEncode = "";

        try {
            oldPassAfterDecode = aesEncryptService.aesDecrypt(userEntity.getPassword());
        } catch (Exception e) {
            return false;
        }

            if(!oldPassAfterDecode.equals(oldPassword)){
                return false;
            }

        try {
            newPassAfterEncode = aesEncryptService.aesEncrypt(newPassword);
        } catch (Exception e) {
            return false;
        }

        userEntity.setPassword(newPassAfterEncode);


        loginUserDbHelper.update(userEntity);

        return true;
    }
}
