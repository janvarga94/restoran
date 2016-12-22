package init.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import init.podaci.Restorani;

/**
 * Created by Svetozar Stojkovic on 11/29/2016.
 */
@Service
public class ServiceRestorani {

    @Autowired
    private Restorani restorani;

    public void getRestorani(){
    }

    public void addRestoran(){
        //verifikacija i validacija
    }
}
