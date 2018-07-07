package business.common.repository;

import business.common.entity.logentity.LogEntity;
import business.common.entity.missionentity.HotMissionEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "hotmission")
@Qualifier("HotMissionRepository")
public interface HotMissionRepository extends CrudRepository<HotMissionEntity,Long> {

    @Query(value="select * from hotmission",nativeQuery=true)
    HotMissionEntity[] getAll();
}
