package init.repositories;

import init.dtos.*;
import init.modelFromDB.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public boolean dodajReon(ReonDTO reonDTO,Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        ReonEntity reon = new ReonEntity();
        reon.setIdRestorana(id);
        reon.setIdReona(reonDTO.id);
        reon.setOpis(reonDTO.opis);

        boolean uspeh = true;

        session.save(reon);

        try{
            session.flush();
        }
        catch(Exception e){

        uspeh = false;
        }finally {
            session.close();
        }

        return uspeh;
    }

    public List<JeloDTO> getJelovnik(Integer idRestorana){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<JeloEntity> list = session.createNativeQuery("SELECT * FROM restorani.jelo where ID_RESTORANA="+ idRestorana + ";" ,JeloEntity.class).getResultList();
        List<JeloDTO> povratanaLista = new ArrayList<JeloDTO>();
        list.forEach(jelo -> {
            JeloDTO jeloDTO = new JeloDTO();
            jeloDTO.idRestorana= jelo.getIdRestorana();
            jeloDTO.idTipJela = jelo.getIdTipaJela();
            jeloDTO.naziv = jelo.getNazivJela();
            jeloDTO.opis = jelo.getOpis();
            jeloDTO.cena = jelo.getCena();
            povratanaLista.add(jeloDTO);

        });

        session.close();
       return povratanaLista;

    }

}
