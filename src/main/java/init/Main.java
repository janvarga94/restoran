package init;

import init.modelFromDB.GostEntity;
import init.modelFromDB.KorisnikEntity;
import init.modelFromDB.RadnikEntity;
import init.modelFromDB.RestoranEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Svetozar Stojkovic on 11/29/2016.
 * This class is beginning  of the project
 */

@SpringBootApplication
public class Main {

    public static Session session;

    public static void main(String[] args){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        popuniKorisnike();

        SpringApplication.run(Main.class, args);

    }

    private static void popuniKorisnike(){
        for(int i=0; i<10; i++){
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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/resursi/restorani").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/zaposleni").allowedOrigins("http://localhost:3000");
                registry.addMapping("/resursi/get_zaposlen").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
