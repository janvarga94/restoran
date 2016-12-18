package init;

import init.modelFromDB.RestoranEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Svetozar Stojkovic on 11/29/2016.
 * This class is beginning  of the project
 */

@SpringBootApplication
public class Main {


    public static void main(String[] args){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for(int i=0; i<10; i++){
            RestoranEntity restoranEntity = new RestoranEntity();
            restoranEntity.setIdRestorana(i);
            restoranEntity.setNaziv("Restoran"+i);
            session.save(restoranEntity);
        }

        SpringApplication.run(Main.class, args);

        session.getTransaction().commit();
        session.close();

    }
}
