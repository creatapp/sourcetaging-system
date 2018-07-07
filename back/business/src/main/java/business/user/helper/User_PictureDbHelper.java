package business.user.helper;

import business.common.entity.pictureentity.PictureEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface User_PictureDbHelper {

    Long[] add(ArrayList<PictureEntity> pictureEntityArrayList);
}
