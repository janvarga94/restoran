package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Stefan on 3/2/2017.
 */
public class PDTO {
    @NotNull
    public String emailPonudjaca;
    @NotNull
    public Double cena;
    @NotNull
    public Integer idPotraznje;
    @NotNull
    public Date rokIsporuke;
    @NotNull
    public String garancija;
    @NotNull
    public Byte prihvacenoOdMenadzera;
    @NotNull
    public Byte ponudjacVideoDalJePonudaPrihvacenaIliOdbijena;
}
