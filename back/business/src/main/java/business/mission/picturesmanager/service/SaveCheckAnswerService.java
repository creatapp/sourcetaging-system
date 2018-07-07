package business.mission.picturesmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface SaveCheckAnswerService {

    String saveCheckAnswer(Long missionId, Long authorId, Long checkerId, Long pictureId, boolean checkAnswer, Integer checkTime);
}
