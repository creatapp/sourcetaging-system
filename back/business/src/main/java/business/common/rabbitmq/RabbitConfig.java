package business.common.rabbitmq;
//
//import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.signup}")
    private String signup;

    @Value("${queue.findpassword}")
    private String findpassword;
//
//    @Bean
//    public Queue signUpQueue(){
//        return new Queue(signup);
//    }
//
//    @Bean
//    public Queue findPasswordQueue(){
//        return new Queue(findpassword);
//    }
}
