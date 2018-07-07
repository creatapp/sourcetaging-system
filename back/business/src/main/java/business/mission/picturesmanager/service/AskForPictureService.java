package business.mission.picturesmanager.service;

import business.mission.picturesmanager.vo.PictureReturnVO;
import org.springframework.stereotype.Component;

@Component
public interface AskForPictureService {

    PictureReturnVO askForPicture(Long missionId, String kind, Long pictureId, Long workerId);
}
