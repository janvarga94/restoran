package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 3/1/2017.
 */
public class stolDTO {
    @NotNull
    public Integer brojStola;
    @NotNull
    public String email;
    @NotNull
    public Integer idReona;
}
