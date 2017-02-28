package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 2/27/2017.
 */
public class MenadzerRestoranaDto {
    @NotNull
    public String ime;
    @NotNull
    public String prezime;
    @NotNull
    public String password;
    @NotNull
    public String email;
    @NotNull
    public int id;

}
