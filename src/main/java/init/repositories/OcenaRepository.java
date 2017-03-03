package init.repositories;

import init.Main;
import init.model.RestoranOcenaDTO;
import init.modelFromDB.OcenaJelaEntity;
import init.modelFromDB.OcenaRestoranaEntity;
import init.modelFromDB.RestoranEntity;
import init.repositories.models.RezervacijaRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static init.Main.session;
import static init.Main.sessionFactory;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Repository
public class OcenaRepository {


    public List<RezervacijaRepo> getRestoraniForGost(String email){

        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "SELECT rezervacija.ID_REZERVACIJE, rezervacija.GOST_EMAIL, restoran.NAZIV, restoran.ID_RESTORANA, rezervacija.POCETAK, rezervacija.KRAJ, sto.BROJ_STOLA, ocena_restorana.OCENA , restoran.VRSTA\n" +
                "\tFROM rezervacija natural join sto natural join reon inner join restoran on restoran.ID_RESTORANA = reon.ID_REONA inner join ocena_restorana on ocena_restorana.ID_RESTORANA = restoran.ID_RESTORANA and ocena_restorana.GOST_EMAIL=rezervacija.GOST_EMAIL inner join poziv_prijatelja on poziv_prijatelja.ID_REZERVACIJE = rezervacija.ID_REZERVACIJE\n" +
                "\twhere rezervacija.GOST_EMAIL = '"+email+"'  \n" +
                "    or\n" +
                "    (poziv_prijatelja.PRVI_EMAIL = '"+email+"' and poziv_prijatelja.DRUGI_EMAIL = rezervacija.GOST_EMAIL and poziv_prijatelja.PRIHVACEN_POZIV = 127)\n" +
                "     or\n" +
                "    (poziv_prijatelja.DRUGI_EMAIL = '"+email+"' and poziv_prijatelja.PRVI_EMAIL = rezervacija.GOST_EMAIL and poziv_prijatelja.PRIHVACEN_POZIV = 127)";
        List<Object[]> results= new ArrayList<>();
        try {
            results = session.createNativeQuery(query).getResultList();
        }catch(Exception e){
            int x = 2;
        }
        List<RezervacijaRepo> returnValue = new ArrayList<RezervacijaRepo>();
        for(Object[] r : results){
            RezervacijaRepo rez = new RezervacijaRepo();
            rez.brojStola = (int) r[6];
            rez.gostEmail = (String) r[1];
            rez.idRezervacije = (int) r[0];
            rez.pocetak = ((Timestamp) r[4]).getTime();
            rez.kraj = ((Timestamp) r[5]).getTime();
            rez.restoranId = (int) r[3];
            rez.restoranNaziv = (String) r[2];
            rez.ocenaRestorana = (int) r[7];
            rez.vrstaRestorana = (String) r[8];

            returnValue.add(rez);
        }

        session.close();
        return returnValue;
    }

    public boolean addOcenaRestorana(OcenaRestoranaEntity ocenaRestoranaEntity){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(ocenaRestoranaEntity.getGostEmail());

        Query query = session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail=:email and ore.idRestorana=:id");
        query.setParameter("email",ocenaRestoranaEntity.getGostEmail());
        query.setParameter("id",ocenaRestoranaEntity.getIdRestorana());

        //OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail='"+ocenaRestoranaEntity.getGostEmail()+"' and ore.idRestorana="+ocenaRestoranaEntity.getIdRestorana());
        OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) query.uniqueResult();

        if (ocena != null) {
            session.delete(ocena);
        }
        session.save(ocenaRestoranaEntity);
        try {
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.close();
            return false;
        }


    }

    public double getOcenaForRestoran(int idRestorana){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Collection<OcenaRestoranaEntity> lista = (Collection<OcenaRestoranaEntity>) session.createQuery("from OcenaRestoranaEntity as ore where ore.idRestorana="+idRestorana).list();

        double ocena = 0;
        for (OcenaRestoranaEntity ore : lista){
            ocena+=ore.getOcena();
        }
        ocena /= lista.size();

        session.close();
        return ocena;
    }



    public boolean addOcenaJela(OcenaJelaEntity ocenaJelaEntity){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(ocenaJelaEntity.getGostEmail());

        Query query = session.createQuery("from OcenaJelaEntity as oje where oje.gostEmail=:email and oje.idRestorana=:id and oje.nazivJela=:nazivJela");
        query.setParameter("email",ocenaJelaEntity.getGostEmail());
        query.setParameter("id",ocenaJelaEntity.getIdRestorana());
        query.setParameter("nazivJela",ocenaJelaEntity.getNazivJela());

        //OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail='"+ocenaRestoranaEntity.getGostEmail()+"' and ore.idRestorana="+ocenaRestoranaEntity.getIdRestorana());
        OcenaJelaEntity ocena = (OcenaJelaEntity) query.uniqueResult();

        if (ocena != null) {
            session.delete(ocena);
        }
        session.save(ocenaJelaEntity);
        try {
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
