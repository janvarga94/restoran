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

/**
 * Created by Svetozar Stojkovic on 11/29/2016.
 * This class is beginning  of the project
 */

@SpringBootApplication
public class Main {

    public static Log log = LogFactory.getLog(Main.class.getName());

    public static Session session;
    public static final String backendUrl = "http://localhost:8080";
    public static final String frontendUrl = "http://localhost:3000";

    public static int pocetak = 0;
    public static int kraj = 10;

    public static void main(String[] args){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

//        popuniRestorane();
//        popuniKorisnike();
//        popuniPorudzbine();
//        popuniRadnike();

        SpringApplication.run(Main.class, args);

    }

    private static void popuniRadnike(){
        for(int i=pocetak; i<kraj; i++){
            KorisnikEntity korisnikEntity = new KorisnikEntity();
            korisnikEntity.setEmail("radnikEmail"+i);
            korisnikEntity.setLozinka("pass"+i);
            korisnikEntity.setIme("Ime"+i);
            korisnikEntity.setPrezime("Prezime"+i);

            session.save(korisnikEntity);

            RadnikEntity radnikEntity = new RadnikEntity();
            radnikEntity.setRadnikEmail("radnikEmail"+i);
            radnikEntity.setIdRestorana(i);
            radnikEntity.setKonfekcijskiBroj(i);
            radnikEntity.setVelicinaObuce(45);

            session.save(radnikEntity);
        }

        session.getTransaction().commit();
    }

    private static void popuniPorudzbine(){
        for(int i=pocetak; i<kraj; i++){
            PorudzbinaEntity porudzbinaEntity = new PorudzbinaEntity();
            porudzbinaEntity.setGostEmail("email"+i);
            porudzbinaEntity.setIdRestorana(i);
            porudzbinaEntity.setIdPorudzbine(i);

            porudzbinaEntity.setKreirana(new Date(System.currentTimeMillis()));

            session.save(porudzbinaEntity);
        }
        session.getTransaction().commit();
    }

    private static void popuniKorisnike(){
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

    private static void popuniRestorane(){
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
