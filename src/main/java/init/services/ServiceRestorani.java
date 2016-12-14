package init.services;

import init.model.Restoran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import init.podaci.Restorani;

import java.util.Collection;

/**
 * Created by Svetozar Stojkovic on 11/29/2016.
 */
@Service
public class ServiceRestorani {

    @Autowired
    private Restorani restorani;

    public Collection<Restoran> getRestorani(){
        return restorani.getRestorani();
    }

    public void addRestoran(Restoran r){
        //verifikacija i validacija
        restorani.addRestoran(r.getNaziv(), r.getVrsta());
    }
}
