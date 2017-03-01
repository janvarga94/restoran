package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
public class PotraznjaNamirnacaEntityPK implements Serializable {
    private int idNamirnice;
    private int idPotraznje;

    @Column(name = "ID_NAMIRNICE")
    @Id
    public int getIdNamirnice() {
        return idNamirnice;
    }

    public void setIdNamirnice(int idNamirnice) {
        this.idNamirnice = idNamirnice;
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

        PotraznjaNamirnacaEntityPK that = (PotraznjaNamirnacaEntityPK) o;

        if (idNamirnice != that.idNamirnice) return false;
        if (idPotraznje != that.idPotraznje) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNamirnice;
        result = 31 * result + idPotraznje;
        return result;
    }
}
