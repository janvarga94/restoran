package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "sanker", schema = "restorani", catalog = "")
public class SankerEntity {
    private int mbr;

    @Id
    @Column(name = "MBR")
    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SankerEntity that = (SankerEntity) o;

        if (mbr != that.mbr) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mbr;
    }
}
