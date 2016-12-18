package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "pozicija_stola", schema = "restorani", catalog = "")
public class PozicijaStolaEntity {
    private Double x;
    private Double y;
    private int idPozicije;

    @Basic
    @Column(name = "X")
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @Basic
    @Column(name = "Y")
    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Id
    @Column(name = "ID_POZICIJE")
    public int getIdPozicije() {
        return idPozicije;
    }

    public void setIdPozicije(int idPozicije) {
        this.idPozicije = idPozicije;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PozicijaStolaEntity that = (PozicijaStolaEntity) o;

        if (idPozicije != that.idPozicije) return false;
        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + idPozicije;
        return result;
    }
}
