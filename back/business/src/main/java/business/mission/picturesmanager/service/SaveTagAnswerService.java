package business.mission.picturesmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface SaveTagAnswerService {

    String saveTagAnswer(Long missionId, Long authorId, Long pictureId, String mark, String tag,Long picTime, Integer tagTime);
}
