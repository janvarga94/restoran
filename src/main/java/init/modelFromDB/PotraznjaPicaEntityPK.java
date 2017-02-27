package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
public class PotraznjaPicaEntityPK implements Serializable {
    private String naziv;
    private int idPotraznje;

    @Column(name = "NAZIV")
    @Id
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Column(name = "ID_POTRAZNJE")
    @Id
    public int getIdPotraznje() {
        return idPotraznje;
    }

    public void setIdPotraznje(int idPotraznje) {
        this.idPotraznje = idPotraznje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PotraznjaPicaEntityPK that = (PotraznjaPicaEntityPK) o;

        if (idPotraznje != that.idPotraznje) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = naziv != null ? naziv.hashCode() : 0;
        result = 31 * result + idPotraznje;
        return result;
    }
}
