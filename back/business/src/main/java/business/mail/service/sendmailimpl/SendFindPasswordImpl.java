package business.mail.service.sendmailimpl;

import business.mail.service.SendFindPasswordService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendFindPasswordImpl implements SendFindPasswordService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer configurer;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    @Async("taskExecutor")
    public void sendFindPasswordMail(Long id, String password,String mail) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setTo(mail);
        helper.setSubject("[SECIII]找回密码");

        Map<String, Object> model = new HashMap<>();
        model.put("title","找回密码");
        model.put("abstract","您正在请求找回你的密码，如果不是你的操作，请尽快修改密码");
        model.put("id",id);
        model.put("password",password);

        Template template = configurer.getConfiguration().getTemplate("mail.html");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);

        helper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }
}


















