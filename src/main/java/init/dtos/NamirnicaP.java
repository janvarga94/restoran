package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

/**
 * Created by Stefan on 3/2/2017.
 */
public class NamirnicaP {
    @NotNull
    public Integer idRestorana;
    @NotNull
    public Date doKad;
    @NotNull
    public List<Namirnica> lista;


}
