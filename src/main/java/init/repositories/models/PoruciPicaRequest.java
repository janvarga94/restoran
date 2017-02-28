package init.repositories.models;

import java.util.List;

/**
 * Created by janva on 2/27/2017.
 */
public class PoruciPicaRequest {
    public List<String> naziviPica;
    public String email;
    public int rezervacijaId;
    public boolean spremnoKadaSeDodje = false;
    public int restoranId;
}
