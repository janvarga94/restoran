package init;

import init.modelFromDB.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Svetozar Stojkovic on 11/29/2016.
 * This class is beginning  of the project
 */

@SpringBootApplication
public class Main {

    public static Log log = LogFactory.getLog(Main.class.getName());

    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public static Session session;
    public static final String backendUrl = "https://isazafakultet.herokuapp.com";
    public static final String frontendUrl = "http://localhost:3000";

    public static int pocetak = 0;
    public static int kraj = 10;

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

//        popuniRestorane();
//        popuniKorisnike();
//        popuniPorudzbine();
//        popuniRadnike();
//        popuniSmene();
//        popuniRasporedSmena();
//        popuniTipoveJela();
//        popuniKonKuvSan();
//        popuniReone();
//        popuniReoneUSmeni();
//        popuniStolove();
//        popuniRezervacije();

        SpringApplication.run(Main.class, args);

    }

    private static void popuniReone() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=pocetak; i<kraj; i++) {
            ReonEntity reonEntity = new ReonEntity();
            reonEntity.setIdRestorana(0);
            reonEntity.setIdReona(i);
            reonEntity.setOpis("Opis reona"+i);

            session.save(reonEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniReoneUSmeni() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=pocetak; i<kraj; i++) {
            ReonUSmeniEntity reonUSmeniEntity = new ReonUSmeniEntity();
            reonUSmeniEntity.setIdRestorana(0);
            reonUSmeniEntity.setIdSmene(0);
            reonUSmeniEntity.setIdReona(i);
            reonUSmeniEntity.setKonobarEmail("radnikEmail"+((i/3)*3));

            session.save(reonUSmeniEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniStolove() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=pocetak; i<kraj; i++) {
            StoEntity stoEntity = new StoEntity();
            stoEntity.setIdRestorana(0);
            stoEntity.setBrojStola(i);
            stoEntity.setIdReona(i);


            session.save(stoEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniRezervacije() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=pocetak; i<kraj; i++) {
            RezervacijaEntity rezervacijaEntity = new RezervacijaEntity();
            rezervacijaEntity.setIdRezervacije(i);
            rezervacijaEntity.setBrojStola(i);
            rezervacijaEntity.setPocetak(new Timestamp(System.currentTimeMillis()));
            rezervacijaEntity.setPocetak(new Timestamp(System.currentTimeMillis() + 10000*i));
            rezervacijaEntity.setGostEmail("email"+i);

            session.save(rezervacijaEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniTipoveJela() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=pocetak; i<kraj; i++) {
            TipJelaEntity tipJelaEntity = new TipJelaEntity();
            tipJelaEntity.setIdTipaJela(i);
            tipJelaEntity.setOpis("Veoma extravagantan ukus jela sa povrcem"+i);

            session.save(tipJelaEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniKonKuvSan() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=pocetak; i<kraj; i++) {

            if (i % 3 == 0) {
                KonobarEntity konobarEntity = new KonobarEntity();
                konobarEntity.setKonobarEmail("radnikEmail" + i);

                session.save(konobarEntity);
            } else if (i % 3 == 1) {

                KuvarEntity kuvarEntity = new KuvarEntity();
                kuvarEntity.setKuvarEmail("radnikEmail" + i);
                kuvarEntity.setIdTipaJela(i);

                session.save(kuvarEntity);
            } else {

                SankerEntity sankerEntity = new SankerEntity();
                sankerEntity.setSankerEmail("radnikEmail" + i);

                session.save(sankerEntity);
            }
        }

        session.getTransaction().commit();
    }


    private static void popuniRadnike() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=pocetak; i<kraj; i++){
            KorisnikEntity korisnikEntity = new KorisnikEntity();
            korisnikEntity.setEmail("radnikEmail"+i);
            korisnikEntity.setLozinka("pass"+i);
            korisnikEntity.setIme("Ime"+i);
            korisnikEntity.setPrezime("Prezime"+i);

            session.save(korisnikEntity);

            RadnikEntity radnikEntity = new RadnikEntity();
            radnikEntity.setRadnikEmail("radnikEmail"+i);
            radnikEntity.setIdRestorana(0);
            radnikEntity.setKonfekcijskiBroj(i);
            radnikEntity.setVelicinaObuce(45);

            session.save(radnikEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniPorudzbine() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=pocetak; i<kraj; i++){
            PorudzbinaEntity porudzbinaEntity = new PorudzbinaEntity();
            porudzbinaEntity.setGostEmail("email"+i);
//            porudzbinaEntity.setIdRestorana(0);
            porudzbinaEntity.setIdPorudzbine(i);


            session.save(porudzbinaEntity);
        }
        session.getTransaction().commit();
    }

    private static void popuniKorisnike() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=pocetak; i<kraj; i++){
            KorisnikEntity korisnikEntity = new KorisnikEntity();
            korisnikEntity.setEmail("email"+i);
            korisnikEntity.setLozinka("pass"+i);
            korisnikEntity.setIme("Ime"+i);
            korisnikEntity.setPrezime("Prezime"+i);

            session.save(korisnikEntity);

            GostEntity gostEntity = new GostEntity();
            gostEntity.setGostEmail("email"+i);

            session.save(gostEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniRestorane() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=pocetak; i<kraj; i++){
            RestoranEntity restoranEntity = new RestoranEntity();
            restoranEntity.setIdRestorana(i);
            restoranEntity.setNaziv("Naziv restorana "+i);
            restoranEntity.setOpis("Opis restorana je "+i);
            if (i%2==0) {
                restoranEntity.setVrsta("Veganski");
            } else {
                restoranEntity.setVrsta("Normalan");
            }

            session.save(restoranEntity);

        }
        session.getTransaction().commit();
    }

    private static void popuniSmene() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=pocetak; i<kraj; i++){
            SmenaEntity smenaEntity = new SmenaEntity();
            smenaEntity.setIdRestorana(0);
            smenaEntity.setIdSmene(i);
            smenaEntity.setBrojSmene(i%3);
            smenaEntity.setPecetak(new Date(System.currentTimeMillis()));

            session.save(smenaEntity);
        }
        session.getTransaction().commit();
    }

    private static void popuniRasporedSmena() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=pocetak; i<kraj; i++){
            RasporedRadaEntity rasporedRadaEntity = new RasporedRadaEntity();
            rasporedRadaEntity.setIdSmene(i);
            rasporedRadaEntity.setRadnikEmail("radnikEmail"+i);

            session.save(rasporedRadaEntity);
        }
        session.getTransaction().commit();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/resursi/restorani").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/zaposleni").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/get_zaposlen").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/restorani_for_user").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/add_ocena_restoran").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/ocena_for_restoran").allowedOrigins("http://localhost:3000");
                registry.addMapping("/**");

            }
        };
    }
}
