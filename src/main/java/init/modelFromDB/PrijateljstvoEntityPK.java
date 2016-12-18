package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
public class PrijateljstvoEntityPK implements Serializable {
    private int idPrvog;
    private int idDrugog;

    @Column(name = "ID_PRVOG")
    @Id
    public int getIdPrvog() {
        return idPrvog;
    }

    public void setIdPrvog(int idPrvog) {
        this.idPrvog = idPrvog;
    }

    @Column(name = "ID_DRUGOG")
    @Id
    public int getIdDrugog() {
        return idDrugog;
    }

    public void setIdDrugog(int idDrugog) {
        this.idDrugog = idDrugog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrijateljstvoEntityPK that = (PrijateljstvoEntityPK) o;

        if (idPrvog != that.idPrvog) return false;
        if (idDrugog != that.idDrugog) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrvog;
        result = 31 * result + idDrugog;
        return result;
    }
}
