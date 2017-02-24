package init.restServices;

import init.modelFromDB.StoEntity;
import init.repositories.RestoranRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by janva on 2/24/2017.
 */
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

    @Autowired
    private RestoranRepository restoranRepo;

    @RequestMapping(path="/stolovi", method = RequestMethod.GET)
    public List<StoEntity> GetStoloviRestorana(int restoran){
        return restoranRepo.GetStoloviRestorana(restoran);
    }
}
