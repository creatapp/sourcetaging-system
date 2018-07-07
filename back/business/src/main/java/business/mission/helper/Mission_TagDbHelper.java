package business.mission.helper;

import business.common.entity.tagentity.TagEntity;
import org.springframework.stereotype.Component;


@Component
public interface Mission_TagDbHelper {

    TagEntity findById(long tagId);

    boolean update(TagEntity tagEntity);

    void delete(long tagId);

    Long add(TagEntity tagEntity);
}
