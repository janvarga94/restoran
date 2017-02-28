package init.repositories;

import init.Main;
import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.*;
import init.repositories.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
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

        String query = "SELECT rezervacija.ID_REZERVACIJE, rezervacija.GOST_EMAIL, restoran.NAZIV, restoran.ID_RESTORANA, rezervacija.POCETAK, rezervacija.KRAJ, sto.BROJ_STOLA, ocena_restorana.OCENA , restoran.VRSTA\n" +
                "FROM rezervacija natural join sto natural join reon inner join restoran on restoran.ID_RESTORANA = reon.ID_REONA inner join ocena_restorana on ocena_restorana.ID_RESTORANA = restoran.ID_RESTORANA\n" +
                "where rezervacija.GOST_EMAIL = '" + email + "'";
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
            rez.ocenaRestorana = (int) r[7];
            rez.vrstaRestorana = (String) r[8];

            returnValue.add(rez);
        }

        session.close();
        return returnValue;
    }

    //postupak je takav da se prethodno obrisu sva jela iz porudzbine, i dodaju nova
    public ResponseWithMessageSuccess poruciJelo(PoruciJelaRequest request){

        ResponseWithMessageSuccess response = new ResponseWithMessageSuccess();
        boolean obrisani = obrisiPorudzbineJelaZa(request.rezervacijaId,request.email);
        if(!obrisani){
            response.Message = "Nemozemo obrisati stare porudzbine jela radi postavljanja novih";
            response.Success = false;
            return response;
        }

        int novaPorudzbina;
        PorudzbinaEntity p = new PorudzbinaEntity();
        p.setIdPorudzbine(novaPorudzbina = random.nextInt());
        p.setGostEmail(request.email);
        p.setIdRezervacije(request.rezervacijaId);
        p.setKreirana(new Timestamp(System.currentTimeMillis()));
        if(request.spremnoKadaSeDodje)
            p.setGostZeliSpremnoU(new Timestamp(123));

        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        session.save(p);

        for(String jelo : request.naziviJela){
            JeloUPorudzbiniEntity jup = new JeloUPorudzbiniEntity();
            jup.setIdPorudzbineJela(random.nextInt());
            jup.setIdPorudzbine(novaPorudzbina);

            JeloEntityPK pk = new JeloEntityPK();
            pk.setNazivJela(jelo);
            pk.setIdRestorana(request.restoranId);
            JeloEntity j = session.get(JeloEntity.class, pk);

            jup.setIdRestorana(j.getIdRestorana());
            jup.setNazivJela(jelo);

            session.save(jup);
        }

        try {
            session.flush();
            response.Success = true;
        }catch(Exception e) {
            response.Success = false;
            response.Message = "Neuspesno pravljenje porudzbine jela";
        }
        session.close();
        return response;
    }

    public ResponseWithMessageSuccess poruciPice(PoruciPicaRequest request){

        ResponseWithMessageSuccess response = new ResponseWithMessageSuccess();
        boolean obrisani = obrisiPorudzbinePicaZa(request.rezervacijaId,request.email);
        if(!obrisani){
            response.Message = "Nemozemo obrisati stare porudzbina pica radi postavljanja novih";
            response.Success = false;
            return response;
        }

        int novaPorudzbina;
        PorudzbinaEntity p = new PorudzbinaEntity();
        p.setIdPorudzbine(novaPorudzbina = random.nextInt());
        p.setGostEmail(request.email);
        p.setIdRezervacije(request.rezervacijaId);
        p.setKreirana(new Timestamp(System.currentTimeMillis()));
        if(request.spremnoKadaSeDodje)
            p.setGostZeliSpremnoU(new Timestamp(123));

        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        session.save(p);

        for(String pice : request.naziviPica){
            PiceUPorudzbiniEntity jup = new PiceUPorudzbiniEntity();
            jup.setIdPorudzbinePica(random.nextInt());
            jup.setIdPorudzbine(novaPorudzbina);

            PiceEntityPK pk = new PiceEntityPK();
            pk.setNazivPica(pice);
            pk.setIdRestorana(request.restoranId);
            PiceEntity j = session.get(PiceEntity.class, pk);

            jup.setIdRestorana(j.getIdRestorana());
            jup.setNazivPica(pice);

            session.save(jup);
        }

        try {
            session.flush();
            response.Success = true;
        }catch(Exception e) {
            response.Success = false;
            response.Message = "Neuspesno pravljenje porudzbine pica";
        }
        session.close();
        return response;
    }

    private boolean obrisiPorudzbineJelaZa(int idRezervacije, String gostEmail){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from jelo_u_porudzbini where jelo_u_porudzbini.ID_PORUDZBINE in \n" +
                "\t(select porudzbina.ID_PORUDZBINE from porudzbina where GOST_EMAIL = '"+gostEmail+"' \n" +
                "         and porudzbina.ID_REZERVACIJE = "+idRezervacije+")\n";

        List<JeloUPorudzbiniEntity> jelaUPorudzbini = session.createNativeQuery(query,JeloUPorudzbiniEntity.class).getResultList();

        for(JeloUPorudzbiniEntity jelo : jelaUPorudzbini){
            session.delete(jelo);
        }

        boolean usphe = false;

        try {
            session.flush();
            usphe = true;
        }catch(Exception e){

        }finally {

        }
        session.close();
        return usphe;
    }

    private boolean obrisiPorudzbinePicaZa(int idRezervacije, String gostEmail){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from pice_u_porudzbini where pice_u_porudzbini.ID_PORUDZBINE in \n" +
                "\t(select porudzbina.ID_PORUDZBINE from porudzbina where GOST_EMAIL = '"+gostEmail+"' \n" +
                "         and porudzbina.ID_REZERVACIJE = "+idRezervacije+")\n";

        List<PiceUPorudzbiniEntity> jelaUPorudzbini = session.createNativeQuery(query,PiceUPorudzbiniEntity.class).getResultList();

        for(PiceUPorudzbiniEntity pice : jelaUPorudzbini){
            session.delete(pice);
        }

        boolean usphe = false;

        try {
            session.flush();
            usphe = true;
        }catch(Exception e){

        }finally {

        }
        session.close();
        return usphe;
    }

    public List<JeloEntity> getPorudzbineJelaZa(int idRezervacije, String gostEmail){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from jelo inner join\n" +
                "(select * from jelo_u_porudzbini where jelo_u_porudzbini.ID_PORUDZBINE in \n" +
                "\t(select porudzbina.ID_PORUDZBINE from porudzbina where GOST_EMAIL = '"+gostEmail+"' \n" +
                "         and porudzbina.ID_REZERVACIJE = "+idRezervacije+")\n" +
                ") table1 on jelo.NAZIV_JELA = table1.naziv_jela and jelo.ID_RESTORANA = table1.id_Restorana";

        List<JeloEntity> jela = session.createNativeQuery(query,JeloEntity.class).getResultList();

        session.close();
        return jela;
    }

    public List<PiceEntity> getPorudzbinePicaZa(int idRezervacije, String gostEmail){
        org.hibernate.Session session = Main.sessionFactory.openSession();
        session.beginTransaction();

        String query = "select * from pice inner join\n" +
                "(select * from pice_u_porudzbini where pice_u_porudzbini.ID_PORUDZBINE in \n" +
                "\t(select porudzbina.ID_PORUDZBINE from porudzbina where GOST_EMAIL = '"+gostEmail+"' \n" +
                "         and porudzbina.ID_REZERVACIJE = "+idRezervacije+")\n" +
                ") table1 on pice.NAZIV_PICA = table1.naziv_pica and pice.ID_RESTORANA = table1.id_Restorana";

        List<PiceEntity> jela = session.createNativeQuery(query,PiceEntity.class).getResultList();

        session.close();
        return jela;
    }


}
