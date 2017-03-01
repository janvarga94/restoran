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

    public boolean dodajSto(stolDTO stolDTO,Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        StoEntity sto = new StoEntity();
        sto.setIdRestorana(id);
        sto.setIdReona(stolDTO.idReona);
        sto.setBrojStola(stolDTO.brojStola);

        boolean uspeh = true;

        session.save(sto);

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

    public boolean dodajNamirnicu(ArtikalDTO artikalDTO, Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Integer> lista = new ArrayList<Integer>();
        lista = artikalDTO.lista;

        boolean uspeh = true;

        for(int i = 0; i < lista.size();i++){
            PotraznjaNamirnacaEntity potraznja = new PotraznjaNamirnacaEntity();
            potraznja.setIdNamirnice(lista.get(i));
            potraznja.setIdPotraznje(id);
            potraznja.setDokad(artikalDTO.datum);

            session.save(potraznja);
        }

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

    public boolean dodajJelo(Jdto jdto,Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        StoEntity sto = new StoEntity();
        JeloEntity jelo = new JeloEntity();
        jelo.setIdRestorana(id);
        jelo.setIdTipaJela(id);
        jelo.setOpis(jdto.opis);
        jelo.setNazivJela(jdto.naziv);
        double d = (double) jdto.cena;
        jelo.setCena(d);

        boolean uspeh = true;

        session.save(jelo);

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

    public int getOcenaRestorana(Integer z){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<OcenaRestoranaEntity> ocene = session.createNativeQuery("SELECT * FROM restorani.ocena_restorana where ID_RESTORANA="+ z + ";" ,OcenaRestoranaEntity.class).getResultList();
        ArrayList<Integer> listaOcena = new ArrayList<Integer>();
        int zbir = 0;
        ocene.forEach(ocena->{
            listaOcena.add(ocena.getOcena());
        });

        for(int i = 0; i< listaOcena.size();i++){
            zbir = zbir + listaOcena.get(i);
        }

        return zbir/listaOcena.size();
    }

    public int getOcenaJela(int broj, String naziv){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<OcenaJelaEntity> ocene = session.createNativeQuery("SELECT * FROM restorani.ocena_jela where ID_RESTORANA=" +broj+ "and NAZIV_JELA = "+naziv+ ";" ,OcenaJelaEntity.class).getResultList();
        ArrayList<Integer> listaOcena = new ArrayList<Integer>();
        int zbir = 0;
        ocene.forEach(ocena->{
            listaOcena.add(ocena.getOcena());
        });
        for(int i = 0; i< listaOcena.size();i++){
            zbir = zbir + listaOcena.get(i);
        }

        return zbir/listaOcena.size();

    }

    public List<ReonR> getReone(Integer idRestorana){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ReonEntity> list = session.createNativeQuery("SELECT * FROM restorani.reon where ID_RESTORANA="+ idRestorana + ";" ,ReonEntity.class).getResultList();
        List<ReonR> povratanaLista = new ArrayList<ReonR>();

        list.forEach(reon -> {
            ReonR reonR = new ReonR();
            reonR.idRestorana = reon.getIdRestorana();
            reonR.idReona = reon.getIdReona();
            reonR.opis = reon.getOpis();

            povratanaLista.add(reonR);

        });

        session.close();
        return povratanaLista;

    }

    public List<NamirnicaDTO> getNamirnice(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<NamirnicaEntity> list = session.createNativeQuery("SELECT * FROM restorani.namirnica;" ,NamirnicaEntity.class).getResultList();
        List<NamirnicaDTO> povratanaLista = new ArrayList<NamirnicaDTO>();

        list.forEach(namirnica -> {
            NamirnicaDTO nam = new NamirnicaDTO();
            nam.id= namirnica.getIdNamirnice();
            nam.naziv = namirnica.getNaziv();
            nam.opis = namirnica.getOpis();

            povratanaLista.add(nam);

        });

        return povratanaLista;

    }

    public void getNamirniceUPotraznji(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PotraznjaNamirnacaEntity pne = new PotraznjaNamirnacaEntity();
        List<PotraznjaNamirnacaEntity> list = session.createNativeQuery("SELECT * FROM restorani.potraznja_namirnaca;",PotraznjaNamirnacaEntity.class).getResultList();


    }

}
