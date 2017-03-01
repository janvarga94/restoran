package init.repositories;

import init.Main;
import init.dtos.RestoranDTO;
import init.model.RestoranOcenaDTO;
import init.modelFromDB.*;
import init.repositories.models.PocetakKrajPair;
import init.repositories.models.StatistikaOdRestorana;
import init.repositories.models.StoRepo;
import init.repositories.models.ZbirVremeCount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static init.Main.session;
import static init.Main.sessionFactory;

/**
 * Created by janva on 2/24/2017.
 */
@Repository
public class RestoranRepository {
    public List<StoRepo> GetStoloviRestorana(int restoran){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        String query = "select sto.BROJ_STOLA, rezervacija.ID_REZERVACIJE, rezervacija.POCETAK, rezervacija.KRAJ from reon, restoran, sto left outer join rezervacija on sto.BROJ_STOLA = rezervacija.BROJ_STOLA where\n" +
                "\t sto.ID_REONA = reon.ID_REONA and \n" +
                "\t reon.ID_RESTORANA = restoran.ID_RESTORANA and\n" +
                "\t restoran.ID_RESTORANA = " + restoran;

        List<Object[]> results = session.createNativeQuery(query).getResultList();
        List<StoRepo> returnValue = new ArrayList<StoRepo>();
        for(Object[] r : results){
            StoRepo sto = new StoRepo();
            sto.idStola = (int) r[0];

            PocetakKrajPair pair = new PocetakKrajPair();
            try{
                pair.pocetak = ((Timestamp) r[2]).getTime();
                pair.kraj = ((Timestamp) r[3]).getTime();

                try {   //ovaj try je jer findFist baci exception ako nenadje
                    StoRepo ss = returnValue.stream().filter(a -> a.idStola == sto.idStola).findFirst().get();
                    ss.zauzetost.add(pair);
                }catch(Exception e){
                    sto.zauzetost.add(pair);
                    returnValue.add(sto);
                }
            }catch(Exception e){
                sto.zauzetost.add(pair);
                returnValue.add(sto);
            }
        }

        return returnValue;
    }

    public List<PiceEntity> getPicaRestorana(int restoranId){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from pice where ID_RESTORANA = " + restoranId;
        List<PiceEntity> pica = session.createNativeQuery(query, PiceEntity.class).getResultList();
        session.close();

        return pica;
    }

    public List<JeloEntity> getJelaRestorana(int restoranId){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from jelo where ID_RESTORANA = " + restoranId;
        List<JeloEntity> jela = session.createNativeQuery(query, JeloEntity.class).getResultList();
        session.close();

        return jela;
    }

    public List<RestoranEntity> getSviRestorani(){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        List<RestoranEntity> list = session.createNativeQuery("SELECT * FROM restoran",RestoranEntity.class).getResultList();
        session.close();
        return list;
    }




    public Collection<RestoranEntity> getRestorani(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<RestoranEntity> lista = (List<RestoranEntity>) session.createQuery("from RestoranEntity").list();

        return lista;
    }

    public boolean addRestoran(RestoranDTO r){
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

    public Collection<RadnikEntity> getRadnici(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<RadnikEntity> lista = (List<RadnikEntity>) session.createQuery("from RadnikEntity").list();

        return lista;
    }

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


    public List<Object> getSmenaForRadnik(int idRestorana, int year, int month, int day) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = (List<Object>) session.createQuery("select se.idRestorana, se.idSmene, se.pecetak, rre.radnikEmail, se.brojSmene, ke.ime, ke.prezime, se.brojSmene" +
                " from SmenaEntity as se, RasporedRadaEntity rre, KorisnikEntity ke where se.idRestorana="+idRestorana+" and rre.radnikEmail = ke.email and se.idSmene = rre.idSmene and se.pecetak = '"+year+"-"+month+"-"+day+"'").list();

        if (lista == null) return new ArrayList<>();

        return (List<Object>) lista;
    }

    public List<Object> getStolovi(int idRestorana) {

//        sessionFactory = new Configuration().configure().buildSessionFactory();
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

    public List<Object> getJela(String kuvarMail, int idRestorana) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = session.createNativeQuery("select jelo_u_porudzbini.KUVAR_EMAIL, porudzbina.ID_PORUDZBINE, jelo_u_porudzbini.ID_RESTORANA, jelo.NAZIV_JELA, jelo.OPIS, jelo.CENA,porudzbina.PLACENO, porudzbina.KREIRANA, porudzbina.GOST_ZELI_SPREMNO_U, porudzbina.SPREMNO_U, porudzbina.PRIVACENA_OD_KUVARA_U\n" +
                "from porudzbina inner join jelo_u_porudzbini on porudzbina.ID_PORUDZBINE=jelo_u_porudzbini.ID_PORUDZBINE natural join jelo\n" +
                "where jelo_u_porudzbini.KUVAR_EMAIL='"+kuvarMail+"' and jelo.ID_RESTORANA="+idRestorana).getResultList();


        return (List<Object>) lista;

    }

    public boolean prihvacenoJelo(int idPorudzbine) {

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

    public boolean skuvanoJelo(int idPorudzbine) {

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


    public List<Object> getPica(String sankerEmail, int idRestorana) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object> lista = session.createNativeQuery("select pice_u_porudzbini.SANKER_EMAIL, porudzbina.ID_PORUDZBINE, pice_u_porudzbini.ID_RESTORANA, pice.NAZIV_PICA, pice.OPIS, pice.CENA,porudzbina.PLACENO, porudzbina.KREIRANA, porudzbina.GOST_ZELI_SPREMNO_U, porudzbina.SPREMNO_U, porudzbina.PRIVACENA_OD_KUVARA_U\n" +
                "from porudzbina inner join pice_u_porudzbini on porudzbina.ID_PORUDZBINE=pice_u_porudzbini.ID_PORUDZBINE natural join pice\n" +
                "where pice_u_porudzbini.SANKER_EMAIL='"+sankerEmail+"' and pice.ID_RESTORANA="+idRestorana+" and ((porudzbina.SPREMNO_U < porudzbina.KREIRANA) or porudzbina.SPREMNO_U is null)").getResultList();


        return (List<Object>) lista;

    }

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

    public StatistikaOdRestorana getStatistikaRestorana(int restoran) {
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select count(restoran.NAZIV), date(rezervacija.POCETAK)  from restoran inner join reon on restoran.ID_RESTORANA = reon.ID_RESTORANA inner join sto on sto.ID_REONA = reon.ID_REONA inner join rezervacija on rezervacija.BROJ_STOLA = sto.BROJ_STOLA\n" +
                "\twhere restoran.ID_RESTORANA = " + restoran + "\n" +
                "group by date(rezervacija.POCETAK) ";
        List<Object[]> results = session.createNativeQuery(query).getResultList();
        StatistikaOdRestorana stat = new StatistikaOdRestorana();
        stat.poDanu = new ArrayList<>();
        for(Object[] obj : results){
            ZbirVremeCount zvc = new ZbirVremeCount();
            zvc.zbir = (BigInteger) obj[0];
            zvc.vreme = ((Date) obj[1]).getTime();
            stat.poDanu.add(zvc);
        }

        return stat;
    }


}
