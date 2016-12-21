package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
@Entity
@Table(name = "potraznja_namirnaca", schema = "restorani", catalog = "")
@IdClass(PotraznjaNamirnacaEntityPK.class)
public class PotraznjaNamirnacaEntity {
    private int idNamirnice;
    private int idPotraznje;

    @Id
    @Column(name = "ID_NAMIRNICE")
    public int getIdNamirnice() {
        return idNamirnice;
    }

    public void setIdNamirnice(int idNamirnice) {
        this.idNamirnice = idNamirnice;
    }

    @Id
    @Column(name = "ID_POTRAZNJE")
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

        PotraznjaNamirnacaEntity that = (PotraznjaNamirnacaEntity) o;

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
