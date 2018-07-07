package business.mail.service.sendmailimpl;

import business.mail.service.SendSignUpService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendSignUpImpl implements SendSignUpService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer configurer;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    @Async("taskExecutor")
    public void sendSignUpMail(Long id, String password,String mail) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setTo(mail);
        helper.setSubject("[SECIII]注册");

        Map<String, Object> model = new HashMap<>();
        model.put("title", "注册");
        model.put("abstract", "感谢您注册SECIII数据众包系统，我们已经为你创建新的账号，请尽情使用吧");
        model.put("id", id);
        model.put("password", password);

        Template template = configurer.getConfiguration().getTemplate("mail.html");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }
}
