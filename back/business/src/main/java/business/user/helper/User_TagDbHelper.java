package business.user.helper;

import business.common.entity.tagentity.TagEntity;
import org.springframework.stereotype.Component;


@Component
public interface User_TagDbHelper {

    TagEntity findById(long tagId);

    boolean update(TagEntity tagEntity);

    void delete(long tagId);

    TagEntity add(TagEntity tagEntity);
}
