package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Stefan on 3/1/2017.
 */
public class DobivenaPonuda {
    @NotNull
    public Integer idPotraznje;
    @NotNull
    public Integer cena;
    @NotNull
    public Date dokad;
    @NotNull
    public String email;

}
