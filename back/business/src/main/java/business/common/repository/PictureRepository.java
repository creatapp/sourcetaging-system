package business.common.repository;

import business.common.entity.pictureentity.PictureEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "picture")
@Qualifier("PictureRepository")
public interface PictureRepository extends CrudRepository<PictureEntity,Long> {

}
