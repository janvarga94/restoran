package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 2/27/2017.
 */
public class RestoranDTO {
    @NotNull
    public int idRestorana;
    @NotNull
    public String vrsta;
    @NotNull
    public String naziv;
    @NotNull
    public String opis;
}
