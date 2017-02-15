package init.adapters;

import init.modelFromDB.KorisnikEntity;
import init.repositories.models.KorisnikRepo;

/**
 * Created by janva on 2/9/2017.
 */
public class KorisnikRepoAdapter {
    public static KorisnikRepo Adapt(KorisnikEntity ke){
        KorisnikRepo kr = new KorisnikRepo();
        kr.password = ke.getLozinka();
        kr.email = ke.getEmail();
        kr.prezime = ke.getPrezime();
        kr.ime = ke.getIme();
        return kr;
    }
}
