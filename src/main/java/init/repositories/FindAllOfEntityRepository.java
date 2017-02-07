package init.repositories;

import java.io.Serializable;

/**
 * Created by janva on 2/6/2017.
 */
public interface FindAllOfEntityRepository<T, ID extends Serializable>
{
    Iterable<T> findAll(ID key);
}
