package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "racun", schema = "restorani", catalog = "")
@IdClass(RacunEntityPK.class)
public class RacunEntity {
    private int idPorudzbine;
    private Date kreirana;
    private int idRestorana;
    private String gostEmail;

    @Id
    @Column(name = "ID_PORUDZBINE")
    public int getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(int idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Id
    @Column(name = "KREIRANA")
    public Date getKreirana() {
        return kreirana;
    }

    public void setKreirana(Date kreirana) {
        this.kreirana = kreirana;
    }

    @Id
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Id
    @Column(name = "GOST_EMAIL")
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

        RacunEntity that = (RacunEntity) o;

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
