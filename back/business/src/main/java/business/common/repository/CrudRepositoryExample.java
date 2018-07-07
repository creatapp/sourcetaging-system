package business.common.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudRepositoryExample<T, ID extends Serializable> extends Repository<T, ID> {

    <S extends T> S save(S entity);//保存
    <S extends T> Iterable<S> save(Iterable<S> entities);//批量保存

    T findOne(ID id);//根据id 查询一个对象。返回对象本身，当对象不存在时，返回null
    Iterable<T> findAll();//查询所有的对象
    Iterable<T> findAll(Iterable<ID> ids);//根据id列表 查询所有的对象

    boolean exists(ID id);//根据id 判断对象是否存在
    long count();//计算对象的总个数

    void delete(ID id);//根据id 删除
    void delete(T entity);//删除一个对象
    void delete(Iterable<? extends T> entities);//批量删除，集合对象（后台执行时，一条一条删除）
    void deleteAll();//删除所有 （后台执行时，一条一条删除）
}
