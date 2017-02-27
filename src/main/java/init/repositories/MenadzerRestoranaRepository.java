package init.repositories;

import init.dtos.MenadzerRestoranaDto;
import init.dtos.PonudjacDTO;
import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.KorisnikEntity;
import init.modelFromDB.MenazerRestoranaEntity;
import init.modelFromDB.PonudjacEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

/**
 * Created by Stefan on 2/26/2017.
 */
@Repository
public class MenadzerRestoranaRepository {

    public int getRestoranID(String email){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("OVDE JE MAIL: "+email);
        MenazerRestoranaEntity mrr = session.get(MenazerRestoranaEntity.class,email);

        sessionFactory.close();

        int rezultat = mrr.getIdRestorana();
        return rezultat;
    }

    public ResponseWithMessageSuccess dodajMenadzer(MenadzerRestoranaDto menadzerRestoranaDto){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        MenazerRestoranaEntity menadzerRestorana = new MenazerRestoranaEntity();
        menadzerRestorana.setEmail(menadzerRestoranaDto.email);
        menadzerRestorana.setIdRestorana(menadzerRestoranaDto.id);

        KorisnikEntity korisnik = new KorisnikEntity();
        korisnik.setEmail(menadzerRestoranaDto.email);
        korisnik.setIme(menadzerRestoranaDto.ime);
        korisnik.setPrezime(menadzerRestoranaDto.prezime);
        korisnik.setEmail(menadzerRestoranaDto.email);
        korisnik.setLozinka(menadzerRestoranaDto.password);

        session.save(korisnik);
        session.save(menadzerRestorana);

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

    public ResponseWithMessageSuccess dodajPonudjaca(PonudjacDTO ponudjacDTO){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        ResponseWithMessageSuccess message = new ResponseWithMessageSuccess();
        message.Message = "Uspeh";
        message.Success = true;

        PonudjacEntity ponudjac = new PonudjacEntity();
        ponudjac.setNaziv(ponudjacDTO.naziv);
        ponudjac.setEmail(ponudjacDTO.email);
        ponudjac.setLozinka(ponudjacDTO.lozinka);

        session.save(ponudjac);

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

}
