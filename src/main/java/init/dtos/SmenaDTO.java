package init.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
public class SmenaDTO {

    @NotNull
    public int idRestorana;
    @NotNull
    public int idSmene;
    @NotNull
    public Date datum;
    @NotNull
    public String radnikEmail;
    @NotNull
    public int brojSmene;
    @NotNull
    public String ime;
    @NotNull
    public String prezime;
    @NotNull
    public int zanimanje;

    public SmenaDTO() {}

    public SmenaDTO(int idRestorana, int idSmene, Date datum, String radnikEmail, int brojSmene, String ime, String prezime, int zanimanje) {
        this.idRestorana = idRestorana;
        this.idSmene = idSmene;
        this.datum = datum;
        this.radnikEmail = radnikEmail;
        this.brojSmene = brojSmene;
        this.ime = ime;
        this.prezime = prezime;
        this.zanimanje = zanimanje;
    }

    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getRadnikEmail() {
        return radnikEmail;
    }

    public void setRadnikEmail(String radnikEmail) {
        this.radnikEmail = radnikEmail;
    }

    public int getBrojSmene() {
        return brojSmene;
    }

    public void setBrojSmene(int brojSmene) {
        this.brojSmene = brojSmene;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getZanimanje() {
        return zanimanje;
    }


    public void setZanimanje(int zanimanje){
        this.zanimanje = zanimanje;
    }
}
