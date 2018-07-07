package business.common.repository;

import business.common.entity.exceptionentity.ExceptionEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "exception")
@Qualifier("ExceptionRepository")
public interface ExceptionRepository extends CrudRepository<ExceptionEntity,Long> {

}
