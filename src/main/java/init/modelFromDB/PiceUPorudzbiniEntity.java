package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "pice_u_porudzbini", schema = "restorani", catalog = "")
public class PiceUPorudzbiniEntity {
    private int id;
    private Integer mbrSankera;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MBR_SANKERA")
    public Integer getMbrSankera() {
        return mbrSankera;
    }

    public void setMbrSankera(Integer mbrSankera) {
        this.mbrSankera = mbrSankera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PiceUPorudzbiniEntity that = (PiceUPorudzbiniEntity) o;

        if (id != that.id) return false;
        if (mbrSankera != null ? !mbrSankera.equals(that.mbrSankera) : that.mbrSankera != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (mbrSankera != null ? mbrSankera.hashCode() : 0);
        return result;
    }
}
