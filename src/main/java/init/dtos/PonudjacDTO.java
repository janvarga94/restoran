package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 2/27/2017.
 */
public class PonudjacDTO {
    @NotNull
    public String naziv;
    @NotNull
    public String email;
    @NotNull
    public String lozinka;
}
