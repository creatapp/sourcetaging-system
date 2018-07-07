package business.common.repository;

import business.common.entity.workerentity.WorkerEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Table(name = "table")
@Qualifier("WorkerRepository")
@Repository
public interface    WorkerRepository extends CrudRepository<WorkerEntity,Long> {
}
