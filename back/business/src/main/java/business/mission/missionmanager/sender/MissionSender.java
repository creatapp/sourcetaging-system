package business.mission.missionmanager.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MissionSender {

    /*本类用于与analysis进行通信，使用时候直接调用即可*/


    @Value("${queue.balance}")
    private String balance;

    public boolean useWhenTagFinished(String[] tagIdArray){
//        this.amqpTemplate.convertAndSend(balance,tagIdArray);

        return true;
    }
}
