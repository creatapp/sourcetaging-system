package business.mail.receiver;

import business.common.exception.LogicException;
import business.mail.service.SendSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpReceiver {

    @Autowired
    private SendSignUpService sendSignUpService;

    public void receive(String context){
        String[] info = context.split("___");
        Long id = new Long(info[0]);
        String password = info[1];
        String email = info[2];
        try{
            sendSignUpService.sendSignUpMail(id,password,email);
        }catch (Exception e){
            throw new LogicException("注册邮件发送失败:" + "  id:"+id+",password:"+password
            +",email:"+email);
        }
    }
}
