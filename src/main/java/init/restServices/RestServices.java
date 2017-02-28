package init.restServices;

import init.Main;
import init.dtos.RestoranDTO;
import init.dtos.SmenaDTO;
import init.dtos.StoDTO;
import init.dtos.ZaposleniDTO;
import init.model.RestoranOcenaDTO;
import init.modelFromDB.*;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import init.services.ServiceRestorani;
import sun.rmi.runtime.Log;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import static init.Main.session;


/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is for accessing
 */
@RestController
@RequestMapping("/resursi")
public class RestServices {

    @Autowired
    private ServiceRestorani restorani;

    @RequestMapping(path="/restorani", method = RequestMethod.GET)
    public Collection<RestoranEntity> getRestorani(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<RestoranEntity> lista = (List<RestoranEntity>) session.createQuery("from RestoranEntity").list();

        return lista;
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public boolean addRestoran(@RequestBody RestoranDTO r){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(r.idRestorana);
        System.out.println(r.naziv);

        RestoranEntity restoran = new RestoranEntity();
        restoran.setIdRestorana(r.idRestorana);
        restoran.setNaziv(r.naziv);
        restoran.setOpis(r.opis);
        restoran.setVrsta(r.vrsta);

        session.save(restoran);


        try{
            session.flush();
        }
        catch(Exception e){

            return false;

        }finally {
            session.close();
        }

        return true;
    }

    @RequestMapping(path = "/zaposleni", method=RequestMethod.GET)
    public Collection<RadnikEntity> getRadnici(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<RadnikEntity> lista = (List<RadnikEntity>) session.createQuery("from RadnikEntity").list();

        return lista;
    }

    @RequestMapping(path = "/get_zaposlen", method=RequestMethod.GET)
    public Object getRadnik(String radnikEmail){

        System.out.println(radnikEmail);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select RADNIK_EMAIL, LOZINKA, ime, prezime, KONFEKCIJSKI_BROJ, ID_RESTORANA, VELICINA_OBUCE\n" +
                "from radnik inner join korisnik on radnik.RADNIK_EMAIL=korisnik.EMAIL\n" +
                "where RADNIK_EMAIL='"+radnikEmail+"'");

        Object obj = query.uniqueResult();

        return obj;
    }

    @RequestMapping(path = "/restorani_for_user", method=RequestMethod.GET)
    public Collection<RestoranOcenaDTO> getRestoraniForGost(String email){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Collection<RestoranEntity> kolekc = (Collection<RestoranEntity>) session.createNativeQuery("select * from restoran as r where r.id_restorana = (select jelo_u_porudzbini.id_restorana from porudzbina inner join jelo_u_porudzbini on porudzbina.id_porudzbine=jelo_u_porudzbini.id_porudzbine where porudzbina.gost_email='"+email+"')").list();

        Collection<RestoranOcenaDTO> restOcena = new ArrayList<>();

        for(RestoranEntity restoranEntity : kolekc){
            RestoranOcenaDTO restoranOcenaDTO = new RestoranOcenaDTO(restoranEntity, getOcenaForRestoran(restoranEntity.getIdRestorana()));
            restOcena.add(restoranOcenaDTO);
        }

        return restOcena;
    }

    @RequestMapping(path = "/add_ocena_restoran", method=RequestMethod.POST)
    public void addOcenaRestorana(@RequestBody OcenaRestoranaEntity ocenaRestoranaEntity){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Main.log.info(ocenaRestoranaEntity);

        Query query = session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail=:email and ore.idRestorana=:id");
        query.setParameter("email",ocenaRestoranaEntity.getGostEmail());
        query.setParameter("id",ocenaRestoranaEntity.getIdRestorana());

        //OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail='"+ocenaRestoranaEntity.getGostEmail()+"' and ore.idRestorana="+ocenaRestoranaEntity.getIdRestorana());
        OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) query.uniqueResult();

        if (ocena != null) {
            session.delete(ocena);
        }
        session.save(ocenaRestoranaEntity);

        session.getTransaction().commit();


    }

    @RequestMapping(path = "/ocena_for_restoran", method=RequestMethod.GET)
    public double getOcenaForRestoran(int idRestorana){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Collection<OcenaRestoranaEntity> lista = (Collection<OcenaRestoranaEntity>) session.createQuery("from OcenaRestoranaEntity as ore where ore.idRestorana="+idRestorana).list();

        double ocena = 0;
        for (OcenaRestoranaEntity ore : lista){
            ocena+=ore.getOcena();
        }
        ocena /= lista.size();

        return ocena;
    }

    @RequestMapping(path = "/get_smene", method=RequestMethod.GET)
    public List<Object> getSmenaForRadnik(int idRestorana, int year, int month, int day) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = (List<Object>) session.createQuery("select se.idRestorana, se.idSmene, se.pecetak, rre.radnikEmail, se.brojSmene, ke.ime, ke.prezime, se.brojSmene" +
                " from SmenaEntity as se, RasporedRadaEntity rre, KorisnikEntity ke where se.idRestorana="+idRestorana+" and rre.radnikEmail = ke.email and se.idSmene = rre.idSmene and se.pecetak = '"+year+"-"+month+"-"+day+"'").list();

        if (lista == null) return new ArrayList<>();

        return (List<Object>) lista;
    }

    @RequestMapping(path = "/get_zanimanje", method = RequestMethod.GET)
    public int getZanimanje(String radnikEmail){
        if (isKonobar(radnikEmail)) {
            return 0;
        } else if (isKuvar(radnikEmail)) {
            return 1;
        } else if (isSanker(radnikEmail)) {
            return 2;
        }
        return -1;

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
    @RequestMapping(path = "/get_stolovi", method = RequestMethod.GET)
    public List<Object> getStolovi(int idRestorana) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toString());

        String time = timestamp.toString().substring(0, timestamp.toString().lastIndexOf('.'));

        List<Object> lista = session.createNativeQuery("select re.ID_RESTORANA, sto.ID_REONA, re.opis, sto.BROJ_STOLA, rez.GOST_EMAIL, rez.pocetak, rez.kraj\n" +
                "from Reon re inner join sto ON re.ID_REONA = sto.ID_REONA left outer join Rezervacija rez on sto.BROJ_STOLA = rez.BROJ_STOLA\n" +
                "where ((rez.pocetak < '"+timestamp.toString()+"' and rez.kraj > '"+timestamp.toString()+"') or (rez.GOST_EMAIL is null))  and re.ID_RESTORANA="+idRestorana).getResultList();

        return (List<Object>) lista;


    }
    @RequestMapping(path = "/get_reon", method = RequestMethod.GET)
    public int getReon(int idSmene, int idRestorana, String konobarMail) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("select rus.idReona from ReonUSmeniEntity as rus where rus.konobarEmail='"+konobarMail+"' and rus.idRestorana="+idRestorana+" and rus.idSmene="+idSmene);
        Object value = query.uniqueResult();


        if (value == null)
            return -1;
        else
            return (int) query.uniqueResult();

    }

    @RequestMapping(path = "/jela_za_kuvara", method = RequestMethod.GET)
    public List<Object> getJela(String kuvarMail, int idRestorana) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = session.createNativeQuery("select jelo_u_porudzbini.KUVAR_EMAIL, porudzbina.ID_PORUDZBINE, jelo_u_porudzbini.ID_RESTORANA, jelo.NAZIV_JELA, jelo.OPIS, jelo.CENA,porudzbina.PLACENO, porudzbina.KREIRANA, porudzbina.GOST_ZELI_SPREMNO_U, porudzbina.SPREMNO_U, porudzbina.PRIVACENA_OD_KUVARA_U\n" +
                "from porudzbina inner join jelo_u_porudzbini on porudzbina.ID_PORUDZBINE=jelo_u_porudzbini.ID_PORUDZBINE natural join jelo\n" +
                "where jelo_u_porudzbini.KUVAR_EMAIL='"+kuvarMail+"' and jelo.ID_RESTORANA="+idRestorana).getResultList();


        return (List<Object>) lista;

    }

    @RequestMapping(path = "/prihvaceno_jelo", method = RequestMethod.GET)
    public boolean prihvaceno(int idPorudzbine) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        Query query = session.createNativeQuery("select *\n" +
//                "from porudzbina\n" +
//                "where porudzbina.ID_PORUDZBINE="+idPorudzbine, PorudzbinaEntity.class);



//        PorudzbinaEntity porudzbinaEntity = (PorudzbinaEntity) query.uniqueResult();
        PorudzbinaEntity porudzbinaEntity = session.get(PorudzbinaEntity.class, idPorudzbine);
        porudzbinaEntity.setPrivacenaOdKuvaraU(timestamp);

        System.out.println(porudzbinaEntity);

        session.update(porudzbinaEntity);

        session.getTransaction().commit();

        return true;

    }

    @RequestMapping(path = "/skuvano_jelo", method = RequestMethod.GET)
    public boolean skuvano(int idPorudzbine) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        Query query = session.createNativeQuery("select *\n" +
//                "from porudzbina\n" +
//                "where porudzbina.ID_PORUDZBINE="+idPorudzbine, PorudzbinaEntity.class);
//
//        PorudzbinaEntity porudzbinaEntity = (PorudzbinaEntity) query.uniqueResult();
        PorudzbinaEntity porudzbinaEntity = session.get(PorudzbinaEntity.class, idPorudzbine);
        porudzbinaEntity.setSpremnoU(timestamp);

        System.out.println(porudzbinaEntity);

        session.update(porudzbinaEntity);

        session.getTransaction().commit();

        return true;

    }

    @RequestMapping(path = "/pica_za_sankera", method = RequestMethod.GET)
    public List<Object> getPica(String sankerEmail, int idRestorana) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = session.createNativeQuery("select pice_u_porudzbini.SANKER_EMAIL, porudzbina.ID_PORUDZBINE, pice_u_porudzbini.ID_RESTORANA, pice.NAZIV_PICA, pice.OPIS, pice.CENA,porudzbina.PLACENO, porudzbina.KREIRANA, porudzbina.GOST_ZELI_SPREMNO_U, porudzbina.SPREMNO_U, porudzbina.PRIVACENA_OD_KUVARA_U\n" +
                "from porudzbina inner join pice_u_porudzbini on porudzbina.ID_PORUDZBINE=pice_u_porudzbini.ID_PORUDZBINE natural join pice\n" +
                "where pice_u_porudzbini.SANKER_EMAIL='"+sankerEmail+"' and pice.ID_RESTORANA="+idRestorana+" and ((porudzbina.SPREMNO_U < porudzbina.KREIRANA) or porudzbina.SPREMNO_U is null)").getResultList();


        return (List<Object>) lista;

    }

    @RequestMapping(path = "/spremljeno_pice", method = RequestMethod.GET)
    public boolean spremljenoPice(int idPorudzbine) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        Query query = session.createNativeQuery("select *\n" +
//                "from porudzbina\n" +
//                "where porudzbina.ID_PORUDZBINE="+idPorudzbine, PorudzbinaEntity.class);
//
//        PorudzbinaEntity porudzbinaEntity = (PorudzbinaEntity) query.uniqueResult();
        PorudzbinaEntity porudzbinaEntity = session.get(PorudzbinaEntity.class, idPorudzbine);
        porudzbinaEntity.setSpremnoU(timestamp);

        System.out.println(porudzbinaEntity);

        session.update(porudzbinaEntity);

        session.getTransaction().commit();

        return true;

    }


}
