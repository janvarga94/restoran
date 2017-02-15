package init.restServices;

import init.dtos.ResponseWithMessageSuccess;
import init.repositories.*;
import init.repositories.models.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by janva on 2/9/2017.
 */
@RestController
@RequestMapping("/prijateljstvo")
public class PrijateljstvoController {
    @Autowired
    private PrijateljstvoRepository repository;
    @Autowired
    private PrijateljstvoRepository prijateljstvoRepo;

    @RequestMapping(path="/posaljiZahtevZaPrijateljstvo", method = RequestMethod.GET)
    public ResponseWithMessageSuccess posaljiZahtev(String emailPrvog, String emailDrugog){
        boolean success = repository.posaljiZahtevZaPrijateljstvo(emailPrvog,emailDrugog);
        ResponseWithMessageSuccess r = new ResponseWithMessageSuccess();
        r.Success = success;
        if(!success){
            r.Message = "Neuspesno slanje zahteva :/";
        }

        return r;
    }


    @RequestMapping(path="/prihvatiZahtevZaPrijateljstvo", method = RequestMethod.GET)
    public ResponseWithMessageSuccess prihvatiZahtev(String emailPrvog, String emailDrugog){
        boolean success = repository.prihvatiPrijateljsvo(emailPrvog,emailDrugog);
        ResponseWithMessageSuccess r = new ResponseWithMessageSuccess();
        r.Success = success;
        if(!success){
            r.Message = "Neuspesno prihvatanje zahteva :/";
        }

        return r;
    }

    @RequestMapping(path="/prekiniPrijateljstvo", method = RequestMethod.GET)
    public ResponseWithMessageSuccess prekiniPrijateljstvo(String emailPrvog, String emailDrugog){
        boolean success = repository.prekiniPrijateljstvo(emailPrvog,emailDrugog);
        ResponseWithMessageSuccess r = new ResponseWithMessageSuccess();
        r.Success = success;
        if(!success){
            r.Message = "Neuspesan prekid :/";
        }

        return r;
    }

    @RequestMapping(path="/getPrijatelje", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> getPrijatelje(String email){
        Iterable<KorisnikRepo> list =  prijateljstvoRepo.sviPrijateljiGosta(email);

        return list;
    }

    @RequestMapping(path="/getNepozvaneUPrijateljstvo", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> getNePrijatelje(String email){
        Iterable<KorisnikRepo> list =  prijateljstvoRepo.sviNepozvaniUPrijateljstvoINeprijateljiGosta(email);

        return list;
    }

    @RequestMapping(path="/getPozvaneUPrijateljstvo", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> getZahtevanePrijatelje(String email){
        Iterable<KorisnikRepo> list =  prijateljstvoRepo.sviPozvaniUPrijateljstvo(email);

        return list;
    }

    @RequestMapping(path="/gostPozvanUPrijateljstvoOd", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> gostPozvanUPrijateljstvoOd(String email){
        Iterable<KorisnikRepo> list =  prijateljstvoRepo.gostPozvanUPrijateljstvoOd(email);

        return list;
    }
}
