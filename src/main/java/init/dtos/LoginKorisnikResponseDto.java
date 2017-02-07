package init.dtos;

import init.repositories.models.KorisnikRepo;

/**
 * Created by janva on 1/3/2017.
 */
public class LoginKorisnikResponseDto {
    public String email;
    public String ime;
    public String prezime;
    public String uloga;
    public KorisnikRepo.Uloga Euloga;

}
