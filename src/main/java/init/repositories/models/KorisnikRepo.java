package init.repositories.models;


/**
 * Created by janva on 2/2/2017.
 */

public class KorisnikRepo {
    public String email;
    public String ime;
    public String prezime;
    public String password;
    public String uloga;
    public Uloga Euloga;

    public enum Uloga{
        MENAZER_SISTEMA, MENAZER_RESTORANA, SANKER, KONOBAR, KUVAR, GOST
    }

    public void setUloga(Uloga uuloga){
        Euloga = uuloga;
        switch (uuloga){
            case MENAZER_RESTORANA: uloga = "MENAZER_RESTORANA"; break;
            case MENAZER_SISTEMA: uloga = "MENAZER_SISTEMA"; break;
            case SANKER: uloga = "SANKER"; break;
            case KONOBAR: uloga = "KONOBAR"; break;
            case KUVAR: uloga = "KUVAR"; break;
            case GOST: uloga = "GOST"; break;
        }
    }

    public Uloga getUloga(){
        return Euloga;
    }
}
