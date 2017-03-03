package init.restServices;

import init.dtos.ResponseWithMessageSuccess;
import init.modelFromDB.JeloEntity;
import init.modelFromDB.PiceEntity;
import init.modelFromDB.RezervacijaEntity;
import init.modelFromDB.StoEntity;
import init.repositories.RestoranRepository;
import init.repositories.RezervacijaRepository;
import init.repositories.models.*;
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
    @RequestMapping(path="/poruciJela", method = RequestMethod.POST)
    public ResponseWithMessageSuccess PoruciJela(@RequestBody PoruciJelaRequest req){
        return  rezervacijaRepo.poruciJelo(req);
    }
    @RequestMapping(path="/porucenaJela", method = RequestMethod.GET)
    public List<JeloEntity> GetPorucenaJela(int idRezervacije,String email ){
        return  rezervacijaRepo.getPorudzbineJelaZa(idRezervacije,email);
    }
    @RequestMapping(path="/poruciPica", method = RequestMethod.POST)
    public ResponseWithMessageSuccess PoruciPica(@RequestBody PoruciPicaRequest req){
        return  rezervacijaRepo.poruciPice(req);
    }
    @RequestMapping(path="/porucenaPica", method = RequestMethod.GET)
    public List<PiceEntity> GetPorucenaPica(int idRezervacije,String email ){
        return  rezervacijaRepo.getPorudzbinePicaZa(idRezervacije,email);
    }

    @RequestMapping(path="/poziviIciSaPrijateljima", method = RequestMethod.GET)
    public List<PozivURestoran> GetPorucenaPica(String email ){
        return  rezervacijaRepo.getPoziveIciSaPrijateljima(email);
    }

    @RequestMapping(path="/prihvatiIliOdbijPoziv", method = RequestMethod.GET)
    public ResponseWithMessageSuccess GetPrihvatiOdbijPoziv(String  idPoziva, String prihvati){
        if(Integer.parseInt(prihvati) != 0){
            return rezervacijaRepo.updatePozivURestoran(Integer.parseInt(idPoziva),true);
        }else{
            return rezervacijaRepo.updatePozivURestoran(Integer.parseInt(idPoziva),false);
        }
    }

    @RequestMapping(path="/plati", method = RequestMethod.GET)
    public boolean plati(int idRezervacije, String gostEmail, int ukupnaCena){
        return rezervacijaRepo.plati(idRezervacije, gostEmail, ukupnaCena);
    }
}
