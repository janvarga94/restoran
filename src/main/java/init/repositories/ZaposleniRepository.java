package init.repositories;

import init.repositories.models.KorisnikRepo;
import org.springframework.stereotype.Repository;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Repository
public class ZaposleniRepository implements  CrudRepository<Object, String>{

    @Override
    public <S> S save(S entity) {
        return null;
    }

    @Override
    public Object findOne(String primaryKey) {
        return null;
    }

    @Override
    public Iterable<Object> findAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public boolean exists(String primaryKey) {
        return false;
    }
}
