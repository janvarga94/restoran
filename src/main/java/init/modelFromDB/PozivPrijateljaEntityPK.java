package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
public class PozivPrijateljaEntityPK implements Serializable {
    private String prijateljEmail;
    private int idRestorana;
    private String gostEmail;

    @Column(name = "PRIJATELJ_EMAIL")
    @Id
    public String getPrijateljEmail() {
        return prijateljEmail;
    }

    public void setPrijateljEmail(String prijateljEmail) {
        this.prijateljEmail = prijateljEmail;
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

        PozivPrijateljaEntityPK that = (PozivPrijateljaEntityPK) o;

        if (idRestorana != that.idRestorana) return false;
        if (prijateljEmail != null ? !prijateljEmail.equals(that.prijateljEmail) : that.prijateljEmail != null)
            return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prijateljEmail != null ? prijateljEmail.hashCode() : 0;
        result = 31 * result + idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
