package business.mail.receiver;

import business.common.exception.LogicException;
import business.mail.service.SendFindPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FindPasswordReceiver {

    @Autowired
    private SendFindPasswordService sendFindPasswordService;

    public void receive(String context){
        String[] info = context.split("___");
        Long id = new Long(info[0]);
        String password = info[1];
        String email = info[2];
        try{
            sendFindPasswordService.sendFindPasswordMail(id,password,email);
        }catch (Exception e){
            throw new LogicException("找回密码邮件发送失败");
        }
    }
}
