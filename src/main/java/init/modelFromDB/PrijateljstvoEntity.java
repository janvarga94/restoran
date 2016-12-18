package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "prijateljstvo", schema = "restorani", catalog = "")
@IdClass(PrijateljstvoEntityPK.class)
public class PrijateljstvoEntity {
    private int idPrvog;
    private int idDrugog;

    @Id
    @Column(name = "ID_PRVOG")
    public int getIdPrvog() {
        return idPrvog;
    }

    public void setIdPrvog(int idPrvog) {
        this.idPrvog = idPrvog;
    }

    @Id
    @Column(name = "ID_DRUGOG")
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

        PrijateljstvoEntity that = (PrijateljstvoEntity) o;

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
