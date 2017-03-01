package init.restServices;

import init.Main;
import init.dtos.RestoranDTO;
import init.dtos.SmenaDTO;
import init.dtos.StoDTO;
import init.dtos.ZaposleniDTO;
import init.model.RestoranOcenaDTO;
import init.modelFromDB.*;
import init.repositories.OcenaRepository;
import init.repositories.RestoranRepository;
import init.repositories.ZaposleniRepository;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import init.services.ServiceRestorani;
import sun.rmi.runtime.Log;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import static init.Main.session;
import static init.Main.sessionFactory;


/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is for accessing
 */
@RestController
@RequestMapping("/resursi")
public class RestServices {

    @Autowired
    private RestoranRepository rp;
    @Autowired
    private OcenaRepository op;
    @Autowired
    private ZaposleniRepository zp;

    @RequestMapping(path="/restorani", method = RequestMethod.GET)
    public Collection<RestoranEntity> getRestorani(){
        return rp.getRestorani();
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public boolean addRestoran(@RequestBody RestoranDTO r){
        return rp.addRestoran(r);
    }

    @RequestMapping(path = "/zaposleni", method=RequestMethod.GET)
    public Collection<RadnikEntity> getRadnici(){
        return rp.getRadnici();
    }

    @RequestMapping(path = "/get_zaposlen", method=RequestMethod.GET)
    public Object getRadnik(String radnikEmail){

        return rp.getRadnik(radnikEmail);
    }

    @RequestMapping(path = "/restorani_for_user", method=RequestMethod.GET)
    public Collection<RestoranOcenaDTO> getRestoraniForGost(String email){

       return op.getRestoraniForGost(email);
    }

    @RequestMapping(path = "/add_ocena_restoran", method=RequestMethod.GET)
    public boolean addOcenaRestorana(int idRestorana, int ocena, String gostEmail){

        System.out.println(gostEmail);

        OcenaRestoranaEntity ocenaRestoranaEntity = new OcenaRestoranaEntity();
        ocenaRestoranaEntity.setIdRestorana(idRestorana);
        ocenaRestoranaEntity.setOcena(ocena);
        ocenaRestoranaEntity.setGostEmail(gostEmail);

        return op.addOcenaRestorana(ocenaRestoranaEntity);

    }

    @RequestMapping(path = "/ocena_for_restoran", method=RequestMethod.GET)
    public double getOcenaForRestoran(int idRestorana){

        return op.getOcenaForRestoran(idRestorana);
    }

    @RequestMapping(path = "/get_smene", method=RequestMethod.GET)
    public List<Object> getSmenaForRadnik(int idRestorana, int year, int month, int day) {

        return rp.getSmenaForRadnik(idRestorana, year, month, day);
    }

    @RequestMapping(path = "/get_zanimanje", method = RequestMethod.GET)
    public int getZanimanje(String radnikEmail){
        if (zp.isKonobar(radnikEmail)) {
            return 0;
        } else if (zp.isKuvar(radnikEmail)) {
            return 1;
        } else if (zp.isSanker(radnikEmail)) {
            return 2;
        }
        return -1;

    }

    @RequestMapping(path = "/get_stolovi", method = RequestMethod.GET)
    public List<Object> getStolovi(int idRestorana) {

        return rp.getStolovi(idRestorana);

    }
    @RequestMapping(path = "/get_reon", method = RequestMethod.GET)
    public int getReon(int idSmene, int idRestorana, String konobarMail) {
        return rp.getReon(idSmene, idRestorana, konobarMail);

    }

    @RequestMapping(path = "/jela_za_kuvara", method = RequestMethod.GET)
    public List<Object> getJela(String kuvarMail, int idRestorana) {

        return rp.getJela(kuvarMail, idRestorana);
    }

    @RequestMapping(path = "/prihvaceno_jelo", method = RequestMethod.GET)
    public boolean prihvaceno(int idPorudzbine) {

        return rp.prihvacenoJelo(idPorudzbine);

    }

    @RequestMapping(path = "/skuvano_jelo", method = RequestMethod.GET)
    public boolean skuvano(int idPorudzbine) {

        return rp.skuvanoJelo(idPorudzbine);

    }

    @RequestMapping(path = "/pica_za_sankera", method = RequestMethod.GET)
    public List<Object> getPica(String sankerEmail, int idRestorana) {

        return rp.getPica(sankerEmail, idRestorana);
    }

    @RequestMapping(path = "/spremljeno_pice", method = RequestMethod.GET)
    public boolean spremljenoPice(int idPorudzbine) {

        return rp.spremljenoPice(idPorudzbine);

    }

    @RequestMapping(path = "/get_jela_for_restoran", method = RequestMethod.GET)
    public List<Object> getJelaForRestoran(int idRestorana, String email) {

        return rp.getJelaForRestoran(idRestorana, email);

    }

    @RequestMapping(path = "/oceni_jelo", method=RequestMethod.GET)
    public boolean oceniJelo(String nazivJela, int idRestorana, String email, int ocena){

        System.out.println(email);

        OcenaJelaEntity ocenaJelaEntity = new OcenaJelaEntity();
        ocenaJelaEntity.setNazivJela(nazivJela);
        ocenaJelaEntity.setIdRestorana(idRestorana);
        ocenaJelaEntity.setOcena(ocena);
        ocenaJelaEntity.setGostEmail(email);

        return op.addOcenaJela(ocenaJelaEntity);

    }


}
