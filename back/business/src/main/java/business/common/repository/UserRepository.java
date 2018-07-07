package business.common.repository;

import business.common.entity.userentity.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Component
@Repository
@Table(name = "user")
@Qualifier("UserRepository")
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    //以下这段查询语句很可能有错，只是示意，劳烦改一下
    @Query(value="select * from user where ?1 = email",nativeQuery=true)
    //@Modifying
    UserEntity findByMail(String mail);
}
