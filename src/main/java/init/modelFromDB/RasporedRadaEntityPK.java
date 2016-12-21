package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
public class RasporedRadaEntityPK implements Serializable {
    private int idSmene;
    private Date datumPocetka;
    private String radnikEmail;

    @Column(name = "ID_SMENE")
    @Id
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Column(name = "DATUM_POCETKA")
    @Id
    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
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
        if (datumPocetka != null ? !datumPocetka.equals(that.datumPocetka) : that.datumPocetka != null) return false;
        if (radnikEmail != null ? !radnikEmail.equals(that.radnikEmail) : that.radnikEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSmene;
        result = 31 * result + (datumPocetka != null ? datumPocetka.hashCode() : 0);
        result = 31 * result + (radnikEmail != null ? radnikEmail.hashCode() : 0);
        return result;
    }
}
