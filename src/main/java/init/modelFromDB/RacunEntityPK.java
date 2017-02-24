package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
public class RacunEntityPK implements Serializable {
    private int idPorudzbine;
    private Date kreirana;
    private int idRestorana;
    private String gostEmail;

    @Column(name = "ID_PORUDZBINE")
    @Id
    public int getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(int idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Column(name = "KREIRANA")
    @Id
    public Date getKreirana() {
        return kreirana;
    }

    public void setKreirana(Date kreirana) {
        this.kreirana = kreirana;
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

        RacunEntityPK that = (RacunEntityPK) o;

        if (idPorudzbine != that.idPorudzbine) return false;
        if (idRestorana != that.idRestorana) return false;
        if (kreirana != null ? !kreirana.equals(that.kreirana) : that.kreirana != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPorudzbine;
        result = 31 * result + (kreirana != null ? kreirana.hashCode() : 0);
        result = 31 * result + idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
