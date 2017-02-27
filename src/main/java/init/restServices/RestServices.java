package init.restServices;

import init.Main;
import init.dtos.SmenaDTO;
import init.dtos.StoDTO;
import init.dtos.ZaposleniDTO;
import init.model.RestoranOcenaDTO;
import init.modelFromDB.OcenaRestoranaEntity;
import init.modelFromDB.RadnikEntity;
import init.modelFromDB.RestoranEntity;
import init.modelFromDB.SmenaEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import init.services.ServiceRestorani;
import sun.rmi.runtime.Log;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

        return (List<RestoranEntity>) session.createQuery("from RestoranEntity").list();
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public void addRestoran(RestoranEntity r){
        session.save(r);
        session.getTransaction().commit();
    }

    @RequestMapping(path = "/zaposleni", method=RequestMethod.GET)
    public Collection<RadnikEntity> getRadnici(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        return (List<RadnikEntity>) session.createQuery("from RadnikEntity").list();
    }

    @RequestMapping(path = "/get_zaposlen", method=RequestMethod.GET)
    public Object getRadnik(String radnikEmail){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("select p.radnikEmail, ke.lozinka, ke.ime, ke.prezime, p.konfekcijskiBroj, p.idRestorana, p.velicinaObuce from RadnikEntity as p, KorisnikEntity as ke where ke.email = p.radnikEmail and p.radnikEmail=:email")
                .setParameter("email",radnikEmail);
        System.out.println(query.uniqueResult());
        return (Object) query.uniqueResult();
    }

    @RequestMapping(path = "/restorani_for_user", method=RequestMethod.GET)
    public Collection<RestoranOcenaDTO> getRestoraniForGost(String email){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Collection<RestoranEntity> kolekc = (Collection<RestoranEntity>) session.createQuery("select r from RestoranEntity as r where r.idRestorana = (select p.idRestorana from PorudzbinaEntity as p where p.gostEmail='"+email+"')").list();

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

        session.beginTransaction();
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

        List<Object> lista = session.createNativeQuery("select sto.ID_REONA, re.opis, sto.BROJ_STOLA, rez.GOST_EMAIL, rez.pocetak, rez.kraj\n" +
                "from Sto as sto left outer join Rezervacija rez on sto.BROJ_STOLA = rez.BROJ_STOLA inner join reon_u_smeni rus ON rus.ID_REONA = sto.ID_REONA left outer join Reon re ON re.ID_REONA = sto.ID_REONA\n" +
                "where ((rez.pocetak < '"+timestamp.toString()+"' and rez.kraj > '"+timestamp.toString()+"') or (rez.GOST_EMAIL is null))  and sto.ID_RESTORANA="+idRestorana).getResultList();

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
    public List<Object> getJela(String konobarMail) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = session.createNativeQuery("").getResultList();

        return (List<Object>) lista;

    }
}
