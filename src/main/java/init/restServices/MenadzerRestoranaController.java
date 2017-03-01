package init.restServices;


import init.dtos.*;

import init.model.Jelo;
import init.modelFromDB.JeloEntity;
import init.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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






    @RequestMapping(path="/getNamirnicep", method = RequestMethod.GET)
    public void getNamirniceUPotraznji(){
        mrr.getNamirniceUPotraznji();
    }

}
