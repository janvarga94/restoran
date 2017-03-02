package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Stefan on 3/1/2017.
 */
public class PonudaDTO {
    @NotNull
    public String email;
    @NotNull
    public Integer id;
    @NotNull
    public Date datum;
    @NotNull
    public Integer iznos;

}
