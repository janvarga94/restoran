package init.restServices;

import init.model.Restoran;
import init.modelFromDB.RadnikEntity;
import init.modelFromDB.RestoranEntity;
import init.podaci.Restorani;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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


}
