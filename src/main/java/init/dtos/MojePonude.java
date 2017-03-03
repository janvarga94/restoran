package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Stefan on 3/2/2017.
 */
public class MojePonude {
    @NotNull
    public Date dokad;
    @NotNull
    public byte prihvacena;
    @NotNull
    public String naziv;
    @NotNull
    public Integer cena;
    @NotNull
    public Integer id;


}
