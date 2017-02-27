package init.repositories;

import init.Main;
import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.*;
import init.repositories.models.RezervacijaRepo;
import init.repositories.models.RezervacijaReq;
import init.repositories.models.StoRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by janva on 2/25/2017.
 */
@Repository
public class RezervacijaRepository {
    private Random random = new Random();

    public ResponseWithMessageSuccess rezervisi(RezervacijaReq rezervacijaReq){
        ResponseWithMessageSuccess response = new ResponseWithMessageSuccess();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        int rezervacijaId;
        RezervacijaEntity rezervacija = new RezervacijaEntity();
        rezervacija.setBrojStola(rezervacijaReq.idStola);
        rezervacija.setGostEmail(rezervacijaReq.rezervant);
        rezervacija.setIdRezervacije(rezervacijaId = random.nextInt(80000));
        rezervacija.setKraj(new Timestamp(rezervacijaReq.kraj));
        rezervacija.setPocetak(new Timestamp(rezervacijaReq.pocetak));


        session.save(rezervacija);

        for(String pozvani : rezervacijaReq.pozvaniPrijatelji){
            String query = "select * from prijateljstvo where (prijateljstvo.DRUGI_EMAIL = \'"+ rezervacijaReq.rezervant+"\' and prijateljstvo.PRVI_EMAIL = \'" + pozvani + "\') or (prijateljstvo.DRUGI_EMAIL = \'" + pozvani + "\' and prijateljstvo.PRVI_EMAIL = \'" + rezervacijaReq.rezervant + "\')";
            List<PrijateljstvoEntity> list = session.createNativeQuery(query,PrijateljstvoEntity.class).getResultList();
            if(list.size() == 0){
                session.close();
                response.Success = false;
                response.Message = "Nemozete pozvati gosta koji vam nije prijatelj";
                return  response;
            }

            PrijateljstvoEntity prijateljstvo = list.get(0);
            PozivPrijateljaEntity poziv = new PozivPrijateljaEntity();

            poziv.setPrviEmail(prijateljstvo.getPrviEmail());
            poziv.setDrugiEmail(prijateljstvo.getDrugiEmail());
            poziv.setIdRezervacije(rezervacijaId);
            poziv.setIdPoziva(random.nextInt(800000));
            poziv.setPosiljaocVideoOdgovor(Byte.MIN_VALUE);
            poziv.setPrihvacenPoziv(Byte.MIN_VALUE);
            poziv.setPocetak(new Date(rezervacijaReq.pocetak));

            session.save(poziv);
        }

        try
        {
            session.flush();
            response.Success = true;
        }
        catch(Exception e)
        {
            response.Success = false;
            response.Message = "Nije moguce dodati rezervaciju";
        }
        finally
        {
            session.close();
            return response;
        }
    }

    public List<RezervacijaRepo> getRezervacijeKorisnika(String email){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "SELECT rezervacija.ID_REZERVACIJE, rezervacija.GOST_EMAIL, restoran.NAZIV, restoran.ID_RESTORANA, rezervacija.POCETAK, rezervacija.KRAJ, sto.BROJ_STOLA FROM rezervacija natural join sto natural join reon inner join restoran on restoran.ID_RESTORANA = reon.ID_REONA\n" +
                "\twhere rezervacija.GOST_EMAIL =  '" + email + "'";
        List<Object[]> results= new ArrayList<>();
        try {
           results = session.createNativeQuery(query).getResultList();
        }catch(Exception e){
            int x = 2;
        }
        List<RezervacijaRepo> returnValue = new ArrayList<RezervacijaRepo>();
        for(Object[] r : results){
            RezervacijaRepo rez = new RezervacijaRepo();
            rez.brojStola = (int) r[6];
            rez.gostEmail = (String) r[1];
            rez.idRezervacije = (int) r[0];
            rez.pocetak = ((Timestamp) r[4]).getTime();
            rez.kraj = ((Timestamp) r[5]).getTime();
            rez.restoranId = (int) r[3];
            rez.restoranNaziv = (String) r[2];

            returnValue.add(rez);
        }

        session.close();
        return returnValue;
    }

}
