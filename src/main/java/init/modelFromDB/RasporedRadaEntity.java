package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/27/2017.
 */
@Entity
@Table(name = "raspored_rada", schema = "restorani", catalog = "")
@IdClass(RasporedRadaEntityPK.class)
public class RasporedRadaEntity {
    private int idSmene;
    private String radnikEmail;

    @Id
    @Column(name = "ID_SMENE")
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Id
    @Column(name = "RADNIK_EMAIL")
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

        RasporedRadaEntity that = (RasporedRadaEntity) o;

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
