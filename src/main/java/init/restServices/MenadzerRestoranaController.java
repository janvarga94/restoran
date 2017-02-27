package init.restServices;


import init.dtos.MenadzerRestoranaDto;
import init.dtos.PonudjacDTO;

import init.dtos.ResponseWithMessageSuccess;
import init.dtos.ZaposleniDTO;
import init.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stefan on 2/26/2017.
 */
@RestController
@RequestMapping("/menadzerRestorana")
public class MenadzerRestoranaController {
    @Autowired
    public  MenadzerRestoranaRepository mrr;

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

    @RequestMapping(path="/addmenadzeraRestorana", method = RequestMethod.POST)
    public ResponseWithMessageSuccess dodajMenadzeraRestorana(@RequestBody MenadzerRestoranaDto MenadzerRestoranaDto){

        ResponseWithMessageSuccess rwms = mrr.dodajMenadzer(MenadzerRestoranaDto);

        return rwms;
    }

    @RequestMapping(path="/addPonudjac", method = RequestMethod.POST)
    public void dodajPonudjaca(@RequestBody PonudjacDTO ponudjacDTO){

        ResponseWithMessageSuccess rwms = mrr.dodajPonudjaca(ponudjacDTO);

    }



}
