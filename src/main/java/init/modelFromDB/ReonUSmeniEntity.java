package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "reon_u_smeni", schema = "restorani", catalog = "")
@IdClass(ReonUSmeniEntityPK.class)
public class ReonUSmeniEntity {
    private int mbr;
    private int idSmene;
    private int idReona;

    @Id
    @Column(name = "MBR")
    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    @Id
    @Column(name = "ID_SMENE")
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Id
    @Column(name = "ID_REONA")
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReonUSmeniEntity that = (ReonUSmeniEntity) o;

        if (mbr != that.mbr) return false;
        if (idSmene != that.idSmene) return false;
        if (idReona != that.idReona) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mbr;
        result = 31 * result + idSmene;
        result = 31 * result + idReona;
        return result;
    }
}
