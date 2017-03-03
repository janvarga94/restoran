package init.restServices;


import init.dtos.*;

import init.model.Jelo;
import init.modelFromDB.JeloEntity;
import init.modelFromDB.PonudaEntity;
import init.modelFromDB.PotraznjaNamirnacaEntity;
import init.repositories.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Stefan on 2/26/2017.
 */
@RestController
@RequestMapping("/menadzerRestorana")
public class MenadzerRestoranaController {
    @Autowired
    public MenadzerRestoranaRepository mrr;
    // public  MenadzerRestoranaRepository mrr;

    @RequestMapping(path="/getRestoranID", method = RequestMethod.GET)
    public int getRestoranID(String email){
        System.out.println("A JEL OVDE STIGAO PRVO: "+ email);
        int z = mrr.getRestoranID(email);
        return z;
    }

    @RequestMapping(path="/addZaposlenog", method = RequestMethod.POST)
    public ResponseWithMessageSuccess dodaj(@RequestBody ZaposleniDTO zps){
        System.out.println("Jel dosao");
        int z = mrr.getRestoranID(zps.emailM);

        System.out.println(zps.email);
        System.out.println(zps.ime);

        ZaposleniRepository zr = new ZaposleniRepository();
        ResponseWithMessageSuccess message =  zr.addZaposlenog(zps,z);

        return message;

    }

    @RequestMapping(path="/updateZaposlenog", method = RequestMethod.POST)
    public ResponseWithMessageSuccess update(@RequestBody ZaposleniDTO zps){
        System.out.println("Jel dosao");
        int z = Integer.parseInt(zps.emailM); //ovako sam prosledio idRestorana da ubrzam

        System.out.println(zps.email);
        System.out.println(zps.ime);

        ZaposleniRepository zr = new ZaposleniRepository();
        ResponseWithMessageSuccess message =  zr.updateZaposlenog(zps,z);

        return message;

    }

    @RequestMapping(path="/addmenadzeraRestorana", method = RequestMethod.POST)
    public ResponseWithMessageSuccess dodajMenadzeraRestorana(@RequestBody MenadzerRestoranaDto MenadzerRestoranaDto){

        ResponseWithMessageSuccess rwms = mrr.dodajMenadzer(MenadzerRestoranaDto);

        return rwms;
    }

    @RequestMapping(path="/addPonudjac", method = RequestMethod.POST)
    public void dodajPonudjaca(@RequestBody PonudjacDTO ponudjacDTO){

        ResponseWithMessageSuccess rwms = mrr.dodajPonudjaca(ponudjacDTO);

    }

    @RequestMapping(path="/addReon", method = RequestMethod.POST)
    public boolean dodajReon(@RequestBody ReonDTO reonDTO){

        Integer id = mrr.getRestoranID(reonDTO.emailMenadzeraRestorana);
        boolean uspeh = mrr.dodajReon(reonDTO,id);

        return uspeh;

    }








    @RequestMapping(path="/addStol", method = RequestMethod.POST)
    public boolean dodajSto(@RequestBody stolDTO stolDTO){
        Integer id = mrr.getRestoranID(stolDTO.email);

        boolean uspeh = mrr.dodajSto(stolDTO,id);

        return uspeh;
    }

    @RequestMapping(path="/addNamirnica", method = RequestMethod.POST)
    public boolean dodajNamirnicu(@RequestBody ArtikalDTO artikalDTO){
        Integer id = mrr.getRestoranID(artikalDTO.email);

        boolean uspeh = mrr.dodajNamirnicu(artikalDTO,id);

        return uspeh;
    }

    @RequestMapping(path="/addJelo", method = RequestMethod.POST)
    public boolean dodajJelo(@RequestBody Jdto jelodto){
        Integer id = mrr.getRestoranID(jelodto.email);


        boolean uspeh = mrr.dodajJelo(jelodto,id);

        return uspeh;
    }

    @RequestMapping(path="/addPonuda", method = RequestMethod.POST)
    public boolean dodajPonudu(@RequestBody PonudaDTO ponudaDTO){

        boolean uspeh = mrr.dodajPonudu(ponudaDTO);

        return uspeh;
    }

    @RequestMapping(path="/prihvacena", method = RequestMethod.GET)
    public void prihavcena( Integer id){
        PDTO p = mrr.getPonuda(id);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PonudaEntity ponuda = new PonudaEntity();
        ponuda.setCena(p.cena);
        ponuda.setIdPotraznje(p.idPotraznje);
        ponuda.setEmailPonudjaca(p.emailPonudjaca);
        ponuda.setRokIsporuke(p.rokIsporuke);
        Byte b = 1;
        ponuda.setPrihvacenoOdMenadzera(b);
        ponuda.setPonudjacVideoDalJePonudaPrihvacenaIliOdbijena(p.ponudjacVideoDalJePonudaPrihvacenaIliOdbijena);
        ponuda.setGarancija(p.garancija);


        session.update(ponuda);

        session.flush();
        session.close();


    }



    @RequestMapping(path="/getJelovnik", method = RequestMethod.GET)
    public List<JeloDTO> getJelovnik(String email){
        System.out.println("A JEL OVDE STIGAO PRVO: "+ email);
        Integer z = mrr.getRestoranID(email);

        List<JeloDTO> povratnaLista=  mrr.getJelovnik(z);

        return povratnaLista;
    }


    @RequestMapping(path="/getOcenaRestorana", method = RequestMethod.GET)
    public int getOcenaRestorana(String email){
        System.out.println("A JEL OVDE STIGAO PRVO: "+ email);
        Integer z = mrr.getRestoranID(email);

        int m = mrr.getOcenaRestorana(z);

        return m;
    }





    @RequestMapping(path="/getReoni", method = RequestMethod.GET)
    public List<ReonR> getReoni(String email){
        System.out.println("REONI:  : "+ email);
        Integer z = mrr.getRestoranID(email);

        List<ReonR> povratnaLista=  mrr.getReone(z);

        return povratnaLista;
    }

    @RequestMapping(path="/getNamirnice", method = RequestMethod.GET)
    public List<NamirnicaDTO> getNamirnice(){

        List<NamirnicaDTO> povratanaLista  = mrr.getNamirnice();

        return povratanaLista;

    }





    @RequestMapping(path="/getOcenaJela", method = RequestMethod.GET)
    public int getOcenaJela(String email,String jelo){
        System.out.println("A JEL OVDE STIGAO PRVO: "+ email+" jelo"+ jelo);
        Integer z = mrr.getRestoranID(email);


        int m = mrr.getOcenaJela(z,jelo);

        return m;
    }






    @RequestMapping(path="/getNamirniceUPotraznji", method = RequestMethod.GET)
    public List<NamirnicaP> getNamirniceUPotraznji(){
        //List<NamirnicaP> lista = mrr.getNamirniceUPotraznji();

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
                    List<Namirnica> l=mrr.getLista(pn.getIdPotraznje());
                    np.lista = l;
                    lista.add(np);
                }
        );

        return lista;

    }

    @RequestMapping(path="/getDobivenePonude", method = RequestMethod.GET)
    public List<DobivenaPonuda>  getDobivenePonude(String email){
        Integer z = mrr.getRestoranID(email);

        Date datum = new Date(System.currentTimeMillis());

        List<Object[]> lista = mrr.getDobivenePonude(z);

        List<DobivenaPonuda> povratanaList =new ArrayList<DobivenaPonuda>();

        lista.forEach(element ->
        {
            DobivenaPonuda dp = new DobivenaPonuda();
            dp.idPotraznje = (Integer)element[0];
            double d = (double)element[1];
            int cena = (int)d;
            dp.cena = (Integer)cena;
            dp.dokad = (Date)element[2];
            dp.email = (String) element[3];
         //   if(datum.getTime() < dp.dokad.getTime()) {
                povratanaList.add(dp);
          //  }

        });

        return povratanaList;

    }


    @RequestMapping(path="/getMojePonude", method = RequestMethod.GET)
    public List<MojePonude> getMojePonude(String email){
        List<Object[]> lista = mrr.MojePonude(email);

        List<MojePonude> povratnaLista = new ArrayList<MojePonude>();
        lista.forEach(el->
        {
            MojePonude mp = new MojePonude();
            double d = (double)el[0];
            int k = (int)d;
            mp.cena = (Integer)k;
            mp.dokad = (Date) el[1];
            boolean b = (boolean) el[2];
            if(b==false)
                mp.prihvacena = 0;
            else
                mp.prihvacena = 1;
            mp.naziv = (String)el[3];
            mp.id = (Integer) el[4];

            povratnaLista.add(mp);

        });

        return povratnaLista;

    }


    @RequestMapping(path="/izmeniPonudu", method = RequestMethod.GET)
    public void izmeniPonudu(Integer id,Integer cena){
        PDTO p = mrr.getPonuda(id);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        PonudaEntity ponuda = new PonudaEntity();
        double d = (double)cena;
        Double dd = (Double) d;
        ponuda.setCena(dd);
        ponuda.setIdPotraznje(p.idPotraznje);
        ponuda.setEmailPonudjaca(p.emailPonudjaca);
        ponuda.setRokIsporuke(p.rokIsporuke);
        ponuda.setPrihvacenoOdMenadzera(p.prihvacenoOdMenadzera);
        ponuda.setPonudjacVideoDalJePonudaPrihvacenaIliOdbijena(p.ponudjacVideoDalJePonudaPrihvacenaIliOdbijena);
        ponuda.setGarancija(p.garancija);


        session.update(ponuda);

        session.flush();
        session.close();



    }

}
