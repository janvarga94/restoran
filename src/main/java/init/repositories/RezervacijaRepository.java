package init.repositories;

import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.*;
import init.repositories.models.RezervacijaReq;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
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
        rezervacija.setIdReona(session.get(StoEntity.class,rezervacijaReq.idStola).getIdReona());

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
}
