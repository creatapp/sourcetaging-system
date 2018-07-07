package business.mission.picturesmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface UpdateCheckAnswerService {

    boolean updateCheckAnswer(Long missionId, Long authorId, Long checkerId, Long pictureId, String mark, String tag, boolean checkAnswer, Integer checkTime);
}
