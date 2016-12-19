package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "kuvar", schema = "restorani", catalog = "")
public class KuvarEntity {
    private int mbr;
    private Integer idTipaJela;

    @Id
    @Column(name = "MBR")
    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    @Basic
    @Column(name = "ID_TIPA_JELA")
    public Integer getIdTipaJela() {
        return idTipaJela;
    }

    public void setIdTipaJela(Integer idTipaJela) {
        this.idTipaJela = idTipaJela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KuvarEntity that = (KuvarEntity) o;

        if (mbr != that.mbr) return false;
        if (idTipaJela != null ? !idTipaJela.equals(that.idTipaJela) : that.idTipaJela != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mbr;
        result = 31 * result + (idTipaJela != null ? idTipaJela.hashCode() : 0);
        return result;
    }
}
