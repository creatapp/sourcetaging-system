package business.login.service.serviceimpl;

import business.common.entity.userentity.UserEntity;
import business.common.repository.UserRepository;
import business.login.auth.service.AesEncryptService;
import business.login.service.FetchPasswordService;
import business.mail.service.SendFindPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchPasswordServiceImpl implements FetchPasswordService {

    @Autowired
    UserRepository userDbHelper;

    @Autowired
    private SendFindPasswordService sendFindPasswordService;

    @Autowired
    private AesEncryptService aesEncryptService;

    @Override
    public boolean fetchPassword(String email) {

        UserEntity userEntity= userDbHelper.findByMail(email);
        if(userEntity == null)return false;
        else{
            // 发邮件到邮箱，内容id和密码

            String passAfterDecode = "";
            try {
                 passAfterDecode = aesEncryptService.aesDecrypt(userEntity.getPassword());
            } catch (Exception e) {
                return false;
            }

            boolean re  = true;
            try {
                sendFindPasswordService.sendFindPasswordMail(userEntity.getId(),
                                            passAfterDecode,
                                            userEntity.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return re;
        }
    }
}
