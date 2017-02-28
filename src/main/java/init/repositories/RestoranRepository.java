package init.repositories;

import init.modelFromDB.KorisnikEntity;
import init.modelFromDB.StoEntity;
import init.repositories.models.PocetakKrajPair;
import init.repositories.models.StoRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
                pair.pocetak = ((Date) r[2]).getTime();
                pair.kraj = ((Date) r[3]).getTime();

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
}