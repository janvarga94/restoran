package init.restServices;

import init.model.Restoran;
import init.modelFromDB.OcenaRestoranaEntity;
import init.modelFromDB.PorudzbinaEntity;
import init.modelFromDB.RadnikEntity;
import init.modelFromDB.RestoranEntity;
import init.podaci.Restorani;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import init.services.ServiceRestorani;

import java.util.Collection;
import java.util.List;

import static init.Main.session;

/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is for accessing
 */
@RestController
@RequestMapping("/resursi")
public class RestServices {

    @Autowired
    private ServiceRestorani restorani;

    @RequestMapping(path="/restorani", method = RequestMethod.GET)
    public Collection<RestoranEntity> getRestorani(){
        return (List<RestoranEntity>) session.createQuery("from RestoranEntity").list();
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public void addRestoran(RestoranEntity r){
        session.save(r);
        session.getTransaction().commit();
    }

    @RequestMapping(path = "/zaposleni", method=RequestMethod.GET)
    public Collection<RadnikEntity> getRadnici(){
        return (List<RadnikEntity>) session.createQuery("from RadnikEntity").list();
    }

    @RequestMapping(path = "/get_zaposlen", method=RequestMethod.GET)
    public RadnikEntity getRadnik(String radnikEmail){
        Query query = session.createQuery("select p from RadnikEntity as p where p.radnikEmail=:email")
                .setParameter("email",radnikEmail);
        return (RadnikEntity) query.uniqueResult();
    }

    @RequestMapping(path = "/restorani_for_user", method=RequestMethod.GET)
    public Collection<RestoranEntity> getRestoraniForGost(String email){
        return  (Collection<RestoranEntity>) session.createQuery("select r from RestoranEntity as r where r.idRestorana = (select p.idRestorana from PorudzbinaEntity as p where p.gostEmail='"+email+"')").list();

    }

    @RequestMapping(path = "/add_ocena", method=RequestMethod.POST)
    public void addOcena(OcenaRestoranaEntity ocenaRestoranaEntity){

        OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) session.createQuery("from OcenaRestoranaEntity as or where or.gostEmail="+ocenaRestoranaEntity.getGostEmail()+" and or.idRestorana="+ocenaRestoranaEntity.getIdRestorana());
        if (ocena != null) {
            session.delete(ocena);
        }
        session.save(ocenaRestoranaEntity);
    }
}
