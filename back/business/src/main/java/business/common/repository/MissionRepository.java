package business.common.repository;

import business.common.entity.missionentity.MissionEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "mission")
@Qualifier("MissionRepository")
public interface MissionRepository extends CrudRepository<MissionEntity,Long> {

    //这里是各个属性更新方法或者直接就调用save将实体整体覆盖
    //update -->  save

    @Query(value = "select distinct * from mission where picture_total_num > 0 ",nativeQuery = true)
    //@Modifying
    MissionEntity[] listAllMissions();

    @Query(value="select * from mission where picture_finished_num <> picture_total_num",nativeQuery=true)
    //@Modifying
    MissionEntity[] listUnfinishedMissions();

}
