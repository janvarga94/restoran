package init.restServices;

import init.model.Restoran;
import init.podaci.Restorani;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import init.services.ServiceRestorani;

import java.util.Collection;

/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is for accessing
 */
@RestController
@RequestMapping("/restorani")
public class AccessRestorani {

    @Autowired
    private ServiceRestorani restorani;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Restoran> getRestorani(){
        return restorani.getRestorani();
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public void addRestoran(Restoran r){
        r.getNaziv();
        restorani.addRestoran(r);
    }
}
