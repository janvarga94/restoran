package init.repositories;

import init.modelFromDB.KorisnikEntity;
import init.modelFromDB.StoEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by janva on 2/24/2017.
 */
@Repository
public class RestoranRepository {
    public List<StoEntity> GetStoloviRestorana(int restoran){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from sto inner join reon on sto.ID_REONA = reon.ID_REONA where ID_RESTORANA = " + restoran;

        return session.createNativeQuery(query,StoEntity.class).getResultList();
    }
}
