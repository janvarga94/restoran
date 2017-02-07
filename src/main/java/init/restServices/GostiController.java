package init.restServices;

import init.dtos.LoginKorisnikResponseDto;
import init.repositories.*;
import init.repositories.models.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

}
