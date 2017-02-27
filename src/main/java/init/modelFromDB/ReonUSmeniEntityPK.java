package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 2/27/2017.
 */
public class ReonUSmeniEntityPK implements Serializable {
    private int idSmene;
    private int idReona;
    private int idRestorana;
    private String konobarEmail;

    @Column(name = "ID_SMENE")
    @Id
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Column(name = "ID_REONA")
    @Id
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Column(name = "ID_RESTORANA")
    @Id
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Column(name = "KONOBAR_EMAIL")
    @Id
    public String getKonobarEmail() {
        return konobarEmail;
    }

    public void setKonobarEmail(String konobarEmail) {
        this.konobarEmail = konobarEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReonUSmeniEntityPK that = (ReonUSmeniEntityPK) o;

        if (idSmene != that.idSmene) return false;
        if (idReona != that.idReona) return false;
        if (idRestorana != that.idRestorana) return false;
        if (konobarEmail != null ? !konobarEmail.equals(that.konobarEmail) : that.konobarEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSmene;
        result = 31 * result + idReona;
        result = 31 * result + idRestorana;
        result = 31 * result + (konobarEmail != null ? konobarEmail.hashCode() : 0);
        return result;
    }
}
