package init.repositories;

import init.modelFromDB.GostEntity;
import init.modelFromDB.KorisnikEntity;
import init.modelFromDB.PrijateljstvoEntity;
import init.repositories.models.KorisnikRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janva on 2/6/2017.
 */
@Repository
public class FriendsOfGuestRepository implements FindAllOfEntityRepository<KorisnikRepo,String> {
    @Override
    public Iterable<KorisnikRepo> findAll(String email) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<KorisnikEntity> list = session.createNativeQuery("select * from korisnik where korisnik.EMAIL in\n" +
                "(\n" +
                "\tselect prijateljstvo.DRUGI_EMAIL from gost inner join prijateljstvo on gost.GOST_EMAIL = prijateljstvo.PRVI_EMAIL where gost.GOST_EMAIL = '"+email+"' and prijateljstvo.PRIHVACENO is not null\n" +
                "\tunion\n" +
                "\tselect prijateljstvo.PRVI_EMAIL from gost inner join prijateljstvo on gost.GOST_EMAIL = prijateljstvo.DRUGI_EMAIL where gost.GOST_EMAIL = '"+email+"' and prijateljstvo.PRIHVACENO is not null\n" +
                ")",KorisnikEntity.class).getResultList();
        ArrayList<KorisnikRepo> result = new ArrayList<KorisnikRepo>();

        for(KorisnikEntity ke : list){
            KorisnikRepo k= new KorisnikRepo();
            k.ime = ke.getIme();
            k.password = ke.getPrezime();
            k.email = ke.getEmail();
            result.add(k);
        }

        return result;
    }
}
