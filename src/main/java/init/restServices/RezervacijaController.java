package init.restServices;

import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.JeloEntity;
import init.modelFromDB.PiceEntity;
import init.modelFromDB.RezervacijaEntity;
import init.modelFromDB.StoEntity;
import init.repositories.RestoranRepository;
import init.repositories.RezervacijaRepository;
import init.repositories.models.RezervacijaRepo;
import init.repositories.models.RezervacijaReq;
import init.repositories.models.StoRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private RezervacijaRepository rezervacijaRepo;

    @RequestMapping(path="/stolovi", method = RequestMethod.GET)
    public List<StoRepo> GetStoloviRestorana(int restoran){
        return  restoranRepo.GetStoloviRestorana(restoran);
    }

    @RequestMapping(path="/rezervisi", method = RequestMethod.POST)
    public ResponseWithMessageSuccess GetStoloviRestorana(@RequestBody RezervacijaReq req){
        return  rezervacijaRepo.rezervisi(req);
    }

    @RequestMapping(path="/jela", method = RequestMethod.GET)
    public List<JeloEntity> GetJelaRestorana(int restoran){
        return  restoranRepo.getJelaRestorana(restoran);
    }

    @RequestMapping(path="/pica", method = RequestMethod.GET)
    public List<PiceEntity> GetPicaRestorana(int restoran){
        return  restoranRepo.getPicaRestorana(restoran);
    }

    @RequestMapping(path="/rezervacije", method = RequestMethod.GET)
    public List<RezervacijaRepo> GetRezervacijaRestorana(String email){
        return  rezervacijaRepo.getRezervacijeKorisnika(email);
    }

}
