package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

/**
 * Created by Stefan on 3/1/2017.
 */
public class ArtikalDTO {
    @NotNull
    public List<Integer> lista;
    @NotNull
    public String email;
    @NotNull
    public Date datum;
}
