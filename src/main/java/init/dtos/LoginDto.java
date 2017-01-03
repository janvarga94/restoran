package init.dtos;

import javax.validation.constraints.NotNull;

/**
 * Created by janva on 1/3/2017.
 */
public class LoginDto {
    @NotNull
    public String email;
    @NotNull
    public String password;
}
