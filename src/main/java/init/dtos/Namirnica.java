package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 3/2/2017.
 */
public class Namirnica {
    @NotNull
    public String naziv;
    @NotNull
    public String opis;
    @NotNull
    public Integer id;
}
