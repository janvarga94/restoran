package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
public class RezervacijaEntityPK implements Serializable {
    private Date pocetak;
    private int brojStola;
    private int idRestorana;
    private String gostEmail;

    @Column(name = "POCETAK")
    @Id
    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    @Column(name = "BROJ_STOLA")
    @Id
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
    }

    @Column(name = "ID_RESTORANA")
    @Id
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Column(name = "GOST_EMAIL")
    @Id
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RezervacijaEntityPK that = (RezervacijaEntityPK) o;

        if (brojStola != that.brojStola) return false;
        if (idRestorana != that.idRestorana) return false;
        if (pocetak != null ? !pocetak.equals(that.pocetak) : that.pocetak != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pocetak != null ? pocetak.hashCode() : 0;
        result = 31 * result + brojStola;
        result = 31 * result + idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
