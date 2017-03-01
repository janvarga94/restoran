package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
public class ZaposleniDTO {
    @NotNull
    public String email;
    @NotNull
    public String pass;
    @NotNull
    public String ime;
    @NotNull
    public String prezime;
    @NotNull
    public Integer konfenkcijskiBroj;
    @NotNull
    public Integer idRestorana;
    @NotNull
    public Integer velicinaObuce;
}
