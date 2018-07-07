package business.mission.picturesmanager.service;

import org.springframework.stereotype.Component;

@Component
public interface UploadPicturesService {

    //将传过来的zip的base64转换，之后保存图片，返回Long[] picturesId
    Long[] uploadPictures(String[] pictures, Long missionId);
}
