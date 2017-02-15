package init.repositories;

import init.modelFromDB.KorisnikEntity;
import init.modelFromDB.PrijateljstvoEntity;
import init.modelFromDB.PrijateljstvoEntityPK;
import init.repositories.models.KorisnikRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by janva on 2/2/2017.
 */
@Repository
public class GostRepository implements  CrudRepository<KorisnikRepo, String>  {
    @Override
    public <S extends KorisnikRepo> S save(S entity) {
        return null;
    }

    @Override
    public KorisnikRepo findOne(String primaryKey) {
        return null;
    }

    @Override
    public Iterable<KorisnikRepo> findAll() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<KorisnikEntity> list = session.createNativeQuery("SELECT * FROM korisnik where korisnik.email in (select GOST_EMAIL from gost)",KorisnikEntity.class).getResultList();
        List<KorisnikRepo> listToReturn = new ArrayList<KorisnikRepo>();
        list.forEach(kor -> {
            KorisnikRepo k = new KorisnikRepo();
            k.password = kor.getLozinka();
            k.email = kor.getEmail();
            k.ime = kor.getIme();
            k.uloga = "Gost";
            k.Euloga = KorisnikRepo.Uloga.GOST;

            listToReturn.add(k);
        });

        session.close();
        return listToReturn;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(KorisnikRepo entity) {

    }

    @Override
    public boolean update(KorisnikRepo entity) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();


        KorisnikEntity k = session.get(KorisnikEntity.class, entity.email);

        if(k == null) {
            return false;
        }

        k.setIme(entity.ime);
        k.setPrezime(entity.prezime);

        session.update(k);


        session.close();

        return true;
    }

    @Override
    public boolean exists(String primaryKey) {
        return false;
    }




}
