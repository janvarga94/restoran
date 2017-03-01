package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 3/1/2017.
 */
public class Jdto {
    @NotNull
    public String naziv;
    @NotNull
    public String opis;
    @NotNull
    public Integer cena;
    @NotNull
    public String email;

}
