package init.repositories;

import init.adapters.KorisnikRepoAdapter;
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
 * Created by janva on 2/9/2017.
 */
@Repository
public class PrijateljstvoRepository {

    public boolean posaljiZahtevZaPrijateljstvo(String emailPosiljaoca, String emailPrimaoca){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PrijateljstvoEntity p = new PrijateljstvoEntity();

        p.setPrviEmail(emailPosiljaoca);
        p.setDrugiEmail(emailPrimaoca);

        boolean uspeh = true;
        try{
            session.save(p);
            session.flush();
        }catch(Exception e ){
            uspeh = false;
        }finally {
            session.close();

        }
        return uspeh;
    }

    public boolean prekiniPrijateljstvo(String emailPosiljaoca, String emailPrimaoca){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PrijateljstvoEntityPK pk = new PrijateljstvoEntityPK();
        pk.setPrviEmail(emailPosiljaoca);
        pk.setDrugiEmail(emailPrimaoca);

        PrijateljstvoEntityPK pk2 = new PrijateljstvoEntityPK();
        pk2.setPrviEmail(emailPrimaoca);
        pk2.setDrugiEmail(emailPosiljaoca);


        boolean uspeh = true;
        try{
            PrijateljstvoEntity ppp = session.get(PrijateljstvoEntity.class,pk);
            if(ppp == null)  ppp = session.get(PrijateljstvoEntity.class,pk2);
            session.delete(ppp);
            session.flush();
        }catch(Exception e){
            uspeh = false;
        }
        finally {
            session.close();
        }
        return uspeh;
    }

    public boolean prihvatiPrijateljsvo(String emailPosiljaoca, String emailPrimaoca){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PrijateljstvoEntityPK pk = new PrijateljstvoEntityPK();
        pk.setPrviEmail(emailPosiljaoca);
        pk.setDrugiEmail(emailPrimaoca);

        PrijateljstvoEntity prijateljstvo = session.get(PrijateljstvoEntity.class,pk);

        boolean uspeh = true;
        try{
            prijateljstvo.setPrihvaceno(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            session.update(prijateljstvo);
            session.flush();
        }catch(Exception e ){
            uspeh = false;
        }finally {
            session.close();
        }
        return uspeh;
    }

    public Iterable<KorisnikRepo> sviPrijateljiGosta(String email) {
        String query = "select * from korisnik where korisnik.EMAIL in\n" +
                "(\n" +
                "\tselect prijateljstvo.DRUGI_EMAIL from gost inner join prijateljstvo on gost.GOST_EMAIL = prijateljstvo.PRVI_EMAIL where gost.GOST_EMAIL = '"+email+"' and prijateljstvo.PRIHVACENO is not null\n" +
                "\tunion\n" +
                "\tselect prijateljstvo.PRVI_EMAIL from gost inner join prijateljstvo on gost.GOST_EMAIL = prijateljstvo.DRUGI_EMAIL where gost.GOST_EMAIL = '"+email+"' and prijateljstvo.PRIHVACENO is not null\n" +
                ")";

        return getKorisnikListOfQuery(query);
    }

    public Iterable<KorisnikRepo> sviNepozvaniUPrijateljstvoINeprijateljiGosta(String email) {
        String query = "select * from korisnik inner join gost on korisnik.email = gost.gost_email where korisnik.EMAIL not in\n" +
                "(\n" +
                "\t SELECT prijateljstvo.DRUGI_EMAIL FROM prijateljstvo where prijateljstvo.PRVI_EMAIL = '"+email+"'\n" +
                "     union\n" +
                "     SELECT prijateljstvo.Prvi_email FROM prijateljstvo where prijateljstvo.drugi_email = '"+email+"'\n" +
                ")";

        return getKorisnikListOfQuery(query);
    }

    public Iterable<KorisnikRepo> sviPozvaniUPrijateljstvo(String email) {
       String query = "select * from korisnik where korisnik.EMAIL in\n" +
                "(\n" +
                "\tSELECT prijateljstvo.DRUGI_EMAIL FROM prijateljstvo where prijateljstvo.PRVI_EMAIL = '"+email+"' and prijateljstvo.PRIHVACENO is null\n" +
                ")";

        return getKorisnikListOfQuery(query);
    }

    public Iterable<KorisnikRepo> gostPozvanUPrijateljstvoOd(String email) {
        String query = "select * from korisnik where korisnik.EMAIL in\n" +
                "(\n" +
                "\tSELECT prijateljstvo.prvi_email FROM prijateljstvo where prijateljstvo.drugi_email = '"+email+"' and prijateljstvo.PRIHVACENO is null\n" +
                ")";

        return getKorisnikListOfQuery(query);
    }

    private Iterable<KorisnikRepo> getKorisnikListOfQuery(String query){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<KorisnikEntity> list = session.createNativeQuery(query,KorisnikEntity.class).getResultList();
        ArrayList<KorisnikRepo> result = new ArrayList<KorisnikRepo>();

        for(KorisnikEntity ke : list){
            KorisnikRepo k= KorisnikRepoAdapter.Adapt(ke);
            result.add(k);
        }

        session.close();
        return result;
    }

}
