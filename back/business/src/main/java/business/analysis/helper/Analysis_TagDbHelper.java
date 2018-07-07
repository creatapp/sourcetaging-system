package business.analysis.helper;

import business.common.entity.tagentity.TagEntity;
import org.springframework.stereotype.Component;


@Component
public interface Analysis_TagDbHelper {

    TagEntity findById(long tagId);

    boolean update(TagEntity tagEntity);

    void delete(long tagId);

    TagEntity add(TagEntity tagEntity);
}
