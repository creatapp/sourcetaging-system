package business.mission.helper;

import business.common.entity.pictureentity.PictureEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface Mission_PictureDbHelper {

    Long[] add(ArrayList<PictureEntity> pictureEntityArrayList);

    PictureEntity getPicById(Long picId);
}
