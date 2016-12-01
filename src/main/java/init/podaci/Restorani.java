package init.podaci;

import init.model.Restoran;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is used for placing initial data
 */
@Repository
public class Restorani {

    private static HashMap<Integer, Restoran> restorani;

    static {
        restorani = new HashMap<Integer, Restoran>() {
            {
                put(1, new Restoran("Caribic", "picerija", null, null));
                put(2, new Restoran("Minuta", "meals on wheels", null, null));
                put(3, new Restoran("Cream bar", "palacinkarnica", null, null));
            }
        };
    }

    public Collection<Restoran> getRestorani(){
        return restorani.values();
    }

    /**
     * This method is for adding new restoran
     * @param naziv
     * @param vrsta
     */
    public void addRestoran(String naziv, String vrsta){
        restorani.put(123, new Restoran(naziv, vrsta, null, null));
    }

}
