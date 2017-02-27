package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
public class RasporedRadaEntityPK implements Serializable {
    private int idSmene;
    private String radnikEmail;

    @Column(name = "ID_SMENE")
    @Id
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Column(name = "RADNIK_EMAIL")
    @Id
    public String getRadnikEmail() {
        return radnikEmail;
    }

    public void setRadnikEmail(String radnikEmail) {
        this.radnikEmail = radnikEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RasporedRadaEntityPK that = (RasporedRadaEntityPK) o;

        if (idSmene != that.idSmene) return false;
        if (radnikEmail != null ? !radnikEmail.equals(that.radnikEmail) : that.radnikEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSmene;
        result = 31 * result + (radnikEmail != null ? radnikEmail.hashCode() : 0);
        return result;
    }
}
