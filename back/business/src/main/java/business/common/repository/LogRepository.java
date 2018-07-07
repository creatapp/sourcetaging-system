package business.common.repository;

import business.common.entity.logentity.LogEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "log")
@Qualifier("LogRepository")
public interface LogRepository extends CrudRepository<LogEntity,Long> {

    @Query(value="select * from log where operator_id=?1",nativeQuery=true)
    LogEntity[] findByOperatorId( Long operatorId);

    @Query(value="select * from log",nativeQuery=true)
    LogEntity[] getAll();
}
