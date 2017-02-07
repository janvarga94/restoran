package init.repositories;

import java.io.Serializable;

/**
 * Created by janva on 2/2/2017.
 */
public interface CrudRepository<T, ID extends Serializable>
{

    <S extends T> S save(S entity);

    T findOne(ID primaryKey);

    Iterable<T> findAll();

    Long count();

    void delete(T entity);

    boolean update(T entity);

    boolean exists(ID primaryKey);

    // … more functionality omitted.
}