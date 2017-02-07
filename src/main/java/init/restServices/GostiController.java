package init.restServices;

import init.dtos.LoginKorisnikResponseDto;
import init.dtos.ModifyGostDto;
import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.KorisnikEntity;
import init.repositories.*;
import init.repositories.models.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janva on 2/2/2017.
 */
@RestController
@RequestMapping("/gosti")
public class GostiController {
    @Autowired
    private KorisnikRepository repository;
    @Autowired
    private GostRepository gostRepo;
    @Autowired
    private FriendsOfGuestRepository friendsRepo;
    @Autowired
    private NotFriendsOfGuestRepository notFriendsRepo;
    @Autowired
    private PozvaniUPrijateljstvoGuestRepository pozvaniRepo;

    @RequestMapping(path="/getAll", method = RequestMethod.GET)
    public List<LoginKorisnikResponseDto> getAll(){
        Iterable<KorisnikRepo> list =  gostRepo.findAll();
        List<LoginKorisnikResponseDto> listToReturn = new ArrayList<LoginKorisnikResponseDto>();
        list.forEach(k -> {
            LoginKorisnikResponseDto lk = new LoginKorisnikResponseDto();
            lk.uloga = k.uloga;
            lk.Euloga = k.Euloga;
            lk.ime = k.ime;
            lk.prezime = k.prezime;

            listToReturn.add(lk);
        });

        return listToReturn;
    }


    @RequestMapping(path="/getPrijatelje", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> getPrijatelje(String email){
        Iterable<KorisnikRepo> list =  friendsRepo.findAll(email);

        return list;
    }

    @RequestMapping(path="/getGosteKojiNisuPrijatelji", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> getNePrijatelje(String email){
        Iterable<KorisnikRepo> list =  notFriendsRepo.findAll(email);

        return list;
    }

    @RequestMapping(path="/getGosteKojiCekajuNaOdgovorPrijateljstva", method = RequestMethod.GET)
    public Iterable<KorisnikRepo> getZahtevanePrijatelje(String email){
        Iterable<KorisnikRepo> list =  pozvaniRepo.findAll(email);

        return list;
    }

    @RequestMapping(path="/modifyGosta", method = RequestMethod.POST)
    public ResponseWithMessageSuccess getZahtevanePrijatelje(@RequestBody ModifyGostDto gost){

        KorisnikRepo k = new KorisnikRepo();
        k.ime = gost.ime;
        k.prezime = gost.prezime;
        k.email = gost.email;

        try{
            boolean success = gostRepo.update(k);
            if(!success){
                ResponseWithMessageSuccess response = new ResponseWithMessageSuccess();
                response.Success = false;
                response.Message = "Podaci su nevalidni, neuspesna izmena";
                return response;
            }
        }catch(Exception e){
            ResponseWithMessageSuccess response = new ResponseWithMessageSuccess();
            response.Success = false;
            response.Message = "Podaci su nevalidni, neuspesna izmena";
            return response;
        }


        ResponseWithMessageSuccess response = new ResponseWithMessageSuccess();
        response.Success = true;

        return response;
    }

}
