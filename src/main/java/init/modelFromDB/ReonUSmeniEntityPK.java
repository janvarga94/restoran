package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
public class ReonUSmeniEntityPK implements Serializable {
    private int mbr;
    private int idSmene;
    private int idReona;

    @Column(name = "MBR")
    @Id
    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    @Column(name = "ID_SMENE")
    @Id
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Column(name = "ID_REONA")
    @Id
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

        ReonUSmeniEntityPK that = (ReonUSmeniEntityPK) o;

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
