package business.common.repository;

import business.common.entity.tagentity.TagEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "tag")
@Qualifier("TagRepository")
public interface TagRepository extends CrudRepository<TagEntity,Long> {

}
