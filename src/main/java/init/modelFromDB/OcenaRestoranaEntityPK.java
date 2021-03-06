package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
public class OcenaRestoranaEntityPK implements Serializable {
    private int idRestorana;
    private String gostEmail;

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

        OcenaRestoranaEntityPK that = (OcenaRestoranaEntityPK) o;

        if (idRestorana != that.idRestorana) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
