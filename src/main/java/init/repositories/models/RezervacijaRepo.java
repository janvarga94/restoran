package init.repositories.models;

import init.modelFromDB.RezervacijaEntity;

import java.sql.Timestamp;

/**
 * Created by janva on 2/26/2017.
 */
public class RezervacijaRepo {
    public int brojStola;
    public long pocetak;
    public long kraj;
    public Byte otkazano;
    public String gostEmail;
    public int idRezervacije;
    public int restoranId;
    public String restoranNaziv;
}
