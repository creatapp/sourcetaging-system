package business.mission.picturesmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface UpdateTagAnswerService {

    boolean updateTagAnswer(Long missionId, Long authorId, Long pictureId, String mark, String tag, Integer tagTime);
}
