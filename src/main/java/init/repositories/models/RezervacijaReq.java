package init.repositories.models;

import java.sql.Date;
import java.util.List;

/**
 * Created by janva on 2/25/2017.
 */
public class RezervacijaReq {
    public long pocetak;
    public long kraj;
    public String rezervant;
    public int idStola;
    public List<String> pozvaniPrijatelji;
}
