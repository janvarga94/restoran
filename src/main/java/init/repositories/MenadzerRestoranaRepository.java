package init.repositories;

import init.dtos.*;
import init.modelFromDB.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static init.Main.session;

/**
 * Created by Stefan on 2/26/2017.
 */
@Repository
public class MenadzerRestoranaRepository {

    int i = 0;

    public int getRestoranID(String email){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("OVDE JE MAIL: "+email);
        MenazerRestoranaEntity mrr = session.get(MenazerRestoranaEntity.class,email);

        sessionFactory.close();

        int rezultat = mrr.getIdRestorana();
        return rezultat;
    }

    public ResponseWithMessageSuccess dodajMenadzer(MenadzerRestoranaDto menadzerRestoranaDto){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        MenazerRestoranaEntity menadzerRestorana = new MenazerRestoranaEntity();
        menadzerRestorana.setEmail(menadzerRestoranaDto.email);
        menadzerRestorana.setIdRestorana(menadzerRestoranaDto.id);

        KorisnikEntity korisnik = new KorisnikEntity();
        korisnik.setEmail(menadzerRestoranaDto.email);
        korisnik.setIme(menadzerRestoranaDto.ime);
        korisnik.setPrezime(menadzerRestoranaDto.prezime);
        korisnik.setEmail(menadzerRestoranaDto.email);
        korisnik.setLozinka(menadzerRestoranaDto.password);

        session.save(korisnik);
        session.save(menadzerRestorana);

        ResponseWithMessageSuccess message = new ResponseWithMessageSuccess();
        message.Message = "Uspeh";
        message.Success = true;


        try{
            session.flush();
        }
        catch(Exception e){

            message.Message = "Nije uspelo";
            message.Success = false;

        }finally {
            session.close();
        }

        return message;

    }



    public ResponseWithMessageSuccess dodajPonudjaca(PonudjacDTO ponudjacDTO){



        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        ResponseWithMessageSuccess message = new ResponseWithMessageSuccess();
        message.Message = "Uspeh";
        message.Success = true;

        PonudjacEntity ponudjac = new PonudjacEntity();
        ponudjac.setNaziv(ponudjacDTO.naziv);
        ponudjac.setEmail(ponudjacDTO.email);
        ponudjac.setLozinka(ponudjacDTO.lozinka);

        session.save(ponudjac);

        try{
            session.flush();
        }
        catch(Exception e){

            message.Message = "Nije uspelo";
            message.Success = false;

        }finally {
            session.close();
        }


        return message;
    }

    public boolean dodajReon(ReonDTO reonDTO,Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        ReonEntity reon = new ReonEntity();
        reon.setIdRestorana(id);
        reon.setIdReona(reonDTO.id);
        reon.setOpis(reonDTO.opis);

        boolean uspeh = true;

        session.save(reon);

        try{
            session.flush();
        }
        catch(Exception e){

        uspeh = false;
        }finally {
            session.close();
        }

        return uspeh;
    }



    public boolean dodajNamirnicu(ArtikalDTO artikalDTO, Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Integer> lista = new ArrayList<Integer>();
        lista = artikalDTO.lista;

        boolean uspeh = true;

        for(int i = 0; i < lista.size();i++){
            PotraznjaNamirnacaEntity potraznja = new PotraznjaNamirnacaEntity();
            potraznja.setIdNamirnice(lista.get(i));
            potraznja.setIdPotraznje(id);
            potraznja.setDokad(artikalDTO.datum);

            session.save(potraznja);
        }

        try{
            session.flush();
        }
        catch(Exception e){

            uspeh = false;
        }finally {
            session.close();
        }

        return uspeh;

    }

    public boolean dodajJelo(Jdto jdto,Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        StoEntity sto = new StoEntity();
        JeloEntity jelo = new JeloEntity();
        jelo.setIdRestorana(id);
        jelo.setIdTipaJela(id);
        jelo.setOpis(jdto.opis);
        jelo.setNazivJela(jdto.naziv);
        double d = (double) jdto.cena;
        jelo.setCena(d);

        boolean uspeh = true;

        session.save(jelo);

        try{
            session.flush();
        }
        catch(Exception e){

            uspeh = false;
        }finally {
            session.close();
        }

        return uspeh;


    }

    public boolean dodajSto(stolDTO stolDTO,Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        StoEntity sto = new StoEntity();
        sto.setIdRestorana(id);
        sto.setIdReona(stolDTO.idReona);
        sto.setBrojStola(stolDTO.brojStola);

        boolean uspeh = true;

        session.save(sto);

        try{
            session.flush();
        }
        catch(Exception e){

            uspeh = false;
        }finally {
            session.close();
        }

        return uspeh;
    }

    public void dodajPonuduRestorana(PonudaDTO ponudaDTO){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        Date datum = new Date(System.currentTimeMillis());

        PotraznjaRestoranaEntity restoran = new PotraznjaRestoranaEntity();
        restoran.setIdPotraznje(ponudaDTO.id*14+i);
        restoran.setIdRestorana(ponudaDTO.id);
        restoran.setPocetakPonude(datum);
        restoran.setKrajPonude(ponudaDTO.datum);

        session.save(restoran);



        try{
            session.getTransaction().commit();
        }
        catch(Exception e){

            System.out.println("ovde");
        }finally {
            session.close();
        }


    }

    public boolean dodajPonudu(PonudaDTO ponudaDTO){

        dodajPonuduRestorana(ponudaDTO);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();


        PonudaEntity ponuda = new PonudaEntity();
        Double d = (double) ponudaDTO.iznos;
        ponuda.setCena(d);
        ponuda.setGarancija("Ima");
        Byte b = 0;

        ponuda.setPonudjacVideoDalJePonudaPrihvacenaIliOdbijena(b);
        ponuda.setPrihvacenoOdMenadzera(b);
        ponuda.setRokIsporuke(ponudaDTO.datum);
        ponuda.setEmailPonudjaca(ponudaDTO.email);
        ponuda.setIdPotraznje(ponudaDTO.id*14+i); //ponudaDTO.id+35

        boolean uspeh = true;

        session.save(ponuda);

        try{
            session.getTransaction().commit();
        }
        catch(Exception e){

            uspeh = false;
        }finally {
            session.close();
        }

        i++;

        return uspeh;



    }



    public List<JeloDTO> getJelovnik(Integer idRestorana){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<JeloEntity> list = session.createNativeQuery("SELECT * FROM restorani.jelo where ID_RESTORANA="+ idRestorana + ";" ,JeloEntity.class).getResultList();
        List<JeloDTO> povratanaLista = new ArrayList<JeloDTO>();
        list.forEach(jelo -> {
            JeloDTO jeloDTO = new JeloDTO();
            jeloDTO.idRestorana= jelo.getIdRestorana();
            jeloDTO.idTipJela = jelo.getIdTipaJela();
            jeloDTO.naziv = jelo.getNazivJela();
            jeloDTO.opis = jelo.getOpis();
            jeloDTO.cena = jelo.getCena();
            povratanaLista.add(jeloDTO);

        });

        session.close();
       return povratanaLista;

    }









    public List<NamirnicaDTO> getNamirnice(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<NamirnicaEntity> list = session.createNativeQuery("SELECT * FROM restorani.namirnica;" ,NamirnicaEntity.class).getResultList();
        List<NamirnicaDTO> povratanaLista = new ArrayList<NamirnicaDTO>();

        list.forEach(namirnica -> {
            NamirnicaDTO nam = new NamirnicaDTO();
            nam.id= namirnica.getIdNamirnice();
            nam.naziv = namirnica.getNaziv();
            nam.opis = namirnica.getOpis();

            povratanaLista.add(nam);

        });

        session.flush();
        session.close();

        return povratanaLista;

    }

    public int getOcenaRestorana(Integer z){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<OcenaRestoranaEntity> ocene = session.createNativeQuery("SELECT * FROM restorani.ocena_restorana where ID_RESTORANA="+ z + ";" ,OcenaRestoranaEntity.class).getResultList();
        ArrayList<Integer> listaOcena = new ArrayList<Integer>();
        int zbir = 0;
        ocene.forEach(ocena->{
            listaOcena.add(ocena.getOcena());
        });

        for(int i = 0; i< listaOcena.size();i++){
            zbir = zbir + listaOcena.get(i);
        }

        session.flush();
        session.close();

        return zbir/listaOcena.size();
    }

    public int getOcenaJela(int broj, String naziv){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<OcenaJelaEntity> ocene = session.createNativeQuery("SELECT * FROM restorani.ocena_jela where ID_RESTORANA=" +broj+ "and NAZIV_JELA = "+naziv+ ";" ,OcenaJelaEntity.class).getResultList();
        ArrayList<Integer> listaOcena = new ArrayList<Integer>();
        int zbir = 0;
        ocene.forEach(ocena->{
            listaOcena.add(ocena.getOcena());
        });
        for(int i = 0; i< listaOcena.size();i++){
            zbir = zbir + listaOcena.get(i);
        }



        return zbir/listaOcena.size();

    }

    public List<ReonR> getReone(Integer idRestorana){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ReonEntity> list = session.createNativeQuery("SELECT * FROM restorani.reon where ID_RESTORANA="+ idRestorana + ";" ,ReonEntity.class).getResultList();
        List<ReonR> povratanaLista = new ArrayList<ReonR>();

        list.forEach(reon -> {
            ReonR reonR = new ReonR();
            reonR.idRestorana = reon.getIdRestorana();
            reonR.idReona = reon.getIdReona();
            reonR.opis = reon.getOpis();

            povratanaLista.add(reonR);

        });

        session.close();
        return povratanaLista;

    }

    public List<Namirnica> getLista(Integer id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<NamirnicaEntity> list = session.createNativeQuery("SELECT * FROM restorani.namirnica where ID_NAMIRNICE=" + id + "",NamirnicaEntity.class).getResultList();
        List<Namirnica> lista = new ArrayList<Namirnica>();
        list.forEach(l->{
            Namirnica namirnica = new Namirnica();
            namirnica.id = l.getIdNamirnice();
            namirnica.naziv = l.getNaziv();
            namirnica.opis = l.getOpis();
            lista.add(namirnica);
        });


        session.flush();
        session.close();


        return lista;

    }



    public List<NamirnicaP> getNamirniceUPotraznji(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PotraznjaNamirnacaEntity pne = new PotraznjaNamirnacaEntity();
        List<PotraznjaNamirnacaEntity> list = session.createNativeQuery("SELECT * FROM restorani.potraznja_namirnaca;",PotraznjaNamirnacaEntity.class).getResultList();
        session.close();
        List<NamirnicaP> lista = new ArrayList<NamirnicaP>();
        list.forEach(pn->{
            NamirnicaP np = new NamirnicaP();
                    np.idRestorana = pn.getIdPotraznje();
                    np.doKad = pn.getDokad();
                    List<Namirnica> l=getLista(pn.getIdPotraznje());
                    lista.add(np);
                }
        );



        return lista;


    }

    public List<Object[]> getDobivenePonude(Integer z){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object[]> lista =  session.createNativeQuery("select ponuda.ID_POTRAZNJE, ponuda.CENA, ponuda.ROK_ISPORUKE, ponuda.EMAIL_PONUDJACA\n" +
                "from potraznja_restorana inner join ponuda on potraznja_restorana.ID_POTRAZNJE = ponuda.ID_POTRAZNJE \n" +
                "where potraznja_restorana.ID_RESTORANA = "+ z + " and ponuda.PRIHVACENO_OD_MENADZERA= 0;").getResultList();

        session.flush();
        session.close();

        return lista;
    }

    public List<Object[]> MojePonude(String ponudjac){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

     //   String query = "select ponuda.CENA,ponuda.ROK_ISPORUKE,ponuda.PRIHVACENO_OD_MENADZERA,restoran.naziv\n" +
       //                + "from potraznja_restorana inner join ponuda on ponuda.ID_POTRAZNJE = potraznja_restorana.ID_POTRAZNJE"

        List<Object[]> lista =  session.createNativeQuery("select ponuda.CENA,ponuda.ROK_ISPORUKE,ponuda.PRIHVACENO_OD_MENADZERA,restoran.naziv,ponuda.ID_POTRAZNJE\n" +
                "\tfrom potraznja_restorana inner join ponuda on ponuda.ID_POTRAZNJE = potraznja_restorana.ID_POTRAZNJE\n" +
                "\tinner join restoran on potraznja_restorana.ID_RESTORANA = restoran.ID_RESTORANA\n" +
                "\twhere ponuda.EMAIL_PONUDJACA ='" + ponudjac +"';").getResultList();

        session.flush();
        session.close();

        return lista;
    }

        public PDTO getPonuda(Integer id) {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            org.hibernate.Session session = sessionFactory.openSession();
            session.beginTransaction();

            List<PonudaEntity> list = session.createNativeQuery("SELECT * FROM restorani.ponuda where id_potraznje =" + id + ";", PonudaEntity.class).getResultList();

            List<PDTO> povratnalista = new ArrayList<PDTO>();
            list.forEach(l -> {
                PDTO p = new PDTO();
                p.cena = l.getCena();
                p.emailPonudjaca = l.getEmailPonudjaca();
                p.garancija = l.getGarancija();
                p.idPotraznje = l.getIdPotraznje();
                p.ponudjacVideoDalJePonudaPrihvacenaIliOdbijena = l.getPonudjacVideoDalJePonudaPrihvacenaIliOdbijena();
                p.prihvacenoOdMenadzera = l.getPrihvacenoOdMenadzera();
                p.rokIsporuke = l.getRokIsporuke();
                povratnalista.add(p);
            });

            session.flush();
            session.close();

            return povratnalista.get(0);
        }


}
