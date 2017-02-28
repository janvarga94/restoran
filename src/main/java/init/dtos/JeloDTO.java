package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 2/28/2017.
 */
public class JeloDTO {
    @NotNull
    public String naziv;
    @NotNull
    public Integer idRestorana;
    @NotNull
    public Integer idTipJela;
    @NotNull
    public String opis;
    @NotNull
    public double cena;


}
