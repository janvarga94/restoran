package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
public class ReonEntityPK implements Serializable {
    private int idReona;
    private int idRestorana;

    @Column(name = "ID_REONA")
    @Id
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Column(name = "ID_RESTORANA")
    @Id
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReonEntityPK that = (ReonEntityPK) o;

        if (idReona != that.idReona) return false;
        if (idRestorana != that.idRestorana) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReona;
        result = 31 * result + idRestorana;
        return result;
    }
}
