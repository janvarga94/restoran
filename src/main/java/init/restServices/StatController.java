package init.restServices;

import init.repositories.RestoranRepository;
import init.repositories.RezervacijaRepository;
import init.repositories.models.StatistikaOdRestorana;
import init.repositories.models.StoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by janva on 3/1/2017.
 */
@RestController
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private RestoranRepository restoranRepo;

    @RequestMapping(path="/allStats", method = RequestMethod.GET)
    public StatistikaOdRestorana GetStoloviRestorana(String id){
        return  restoranRepo.getStatistikaRestorana(Integer.parseInt(id));
    }

}
