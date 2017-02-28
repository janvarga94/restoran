package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 2/28/2017.
 */
public class ReonDTO {
    @NotNull
    public String emailMenadzeraRestorana;
    @NotNull
    public Integer id;
    @NotNull
    public String opis;
}
