package init.repositories;

import init.dtos.ResponseWithMessageSuccess;
import init.dtos.ZaposleniDTO;
import init.modelFromDB.*;
import init.repositories.models.KorisnikRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import static init.Main.session;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Repository
public class ZaposleniRepository {

    public ZaposleniRepository(){}


    public ResponseWithMessageSuccess addZaposlenog(ZaposleniDTO zaposlen,int z){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        RadnikEntity radnik = new RadnikEntity();
        radnik.setRadnikEmail(zaposlen.email);
        radnik.setIdRestorana(z);
        radnik.setKonfekcijskiBroj(zaposlen.konfenkcijskiBroj);
        radnik.setVelicinaObuce(zaposlen.velicinaObuce);

        KorisnikEntity korisnik = new KorisnikEntity();
        korisnik.setEmail(zaposlen.email);
        korisnik.setIme(zaposlen.ime);
        korisnik.setPrezime(zaposlen.prezime);
        korisnik.setLozinka(zaposlen.pass);

        session.save(korisnik);
        session.save(radnik);

        if(zaposlen.selectedJob.equals("Kuvar")){
            KuvarEntity kuvar = new KuvarEntity();
            kuvar.setKuvarEmail(zaposlen.email);
            kuvar.setIdTipaJela(0);
            session.save(kuvar);
        }
        else if(zaposlen.selectedJob.equals("Sanker")){
            SankerEntity sanker = new SankerEntity();
            sanker.setSankerEmail(zaposlen.email);
            session.save(sanker);
        }
        else
        {
            KonobarEntity konobar = new KonobarEntity();
            konobar.setKonobarEmail(zaposlen.email);
            session.save(konobar);
        }

        ResponseWithMessageSuccess message = new ResponseWithMessageSuccess();
        message.Message = "Uspeh";
        message.Success = true;



        try{
            session.flush();
        }
        catch(Exception e){
            message.Message = "Nije uspelo";
            message.Success = false;
        }finally {
            session.close();
        }

        return message;

    }

    public ResponseWithMessageSuccess updateZaposlenog(ZaposleniDTO zaposlen, int id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        RadnikEntity radnik = new RadnikEntity();
        radnik.setRadnikEmail(zaposlen.email);
        radnik.setIdRestorana(id);
        radnik.setKonfekcijskiBroj(zaposlen.konfenkcijskiBroj);
        radnik.setVelicinaObuce(zaposlen.velicinaObuce);

        KorisnikEntity korisnik = new KorisnikEntity();
        korisnik.setEmail(zaposlen.email);
        korisnik.setIme(zaposlen.ime);
        korisnik.setPrezime(zaposlen.prezime);
        korisnik.setLozinka(zaposlen.pass);

        session.update(korisnik);
        session.update(radnik);


        ResponseWithMessageSuccess message = new ResponseWithMessageSuccess();
        message.Message = "Uspeh";
        message.Success = true;



        try{
            session.getTransaction().commit();
        } catch(Exception e){
            message.Message = "Nije uspelo";
            message.Success = false;
        }finally {
            session.close();
        }

        return message;

    }


    public boolean isKonobar(String radnikEmail){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("select count(k.konobarEmail) from KonobarEntity as k where k.konobarEmail=:email")
                .setParameter("email",radnikEmail);
        long out = (long) query.uniqueResult();

        if (out != 0)
            return true;
        else
            return false;
    }

    public boolean isKuvar(String radnikEmail){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("select count(k.kuvarEmail) from KuvarEntity as k where k.kuvarEmail=:email")
                .setParameter("email",radnikEmail);
        long out = (long) query.uniqueResult();

        if (out != 0)
            return true;
        else
            return false;
    }

    public boolean isSanker(String radnikEmail){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("select count(s.sankerEmail) from SankerEntity as s where s.sankerEmail=:email")
                .setParameter("email",radnikEmail);
        long out = (long) query.uniqueResult();

        if (out != 0)
            return true;
        else
            return false;
    }

}