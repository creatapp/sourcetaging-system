package business.common.repository;

import business.common.entity.requesterentity.RequesterEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "requester")
@Qualifier("RequesterRepository")
public interface RequesterRepository extends CrudRepository<RequesterEntity,Long> {
}
