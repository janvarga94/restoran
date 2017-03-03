package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 3/2/2017.
 */
public class ZakazivanjeDanaDTO {

    @NotNull
    public String email;
    @NotNull
    public Integer idRestorana;
    @NotNull
    public Date datumPocetka;
    @NotNull
    public Integer brojDana;
    @NotNull
    public Integer smena;
    @NotNull
    public Integer reon;
}
