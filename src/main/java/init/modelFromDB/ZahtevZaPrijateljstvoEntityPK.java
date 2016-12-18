package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
public class ZahtevZaPrijateljstvoEntityPK implements Serializable {
    private int idPosiljaoca;
    private int idPrimaoca;

    @Column(name = "ID_POSILJAOCA")
    @Id
    public int getIdPosiljaoca() {
        return idPosiljaoca;
    }

    public void setIdPosiljaoca(int idPosiljaoca) {
        this.idPosiljaoca = idPosiljaoca;
    }

    @Column(name = "ID_PRIMAOCA")
    @Id
    public int getIdPrimaoca() {
        return idPrimaoca;
    }

    public void setIdPrimaoca(int idPrimaoca) {
        this.idPrimaoca = idPrimaoca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZahtevZaPrijateljstvoEntityPK that = (ZahtevZaPrijateljstvoEntityPK) o;

        if (idPosiljaoca != that.idPosiljaoca) return false;
        if (idPrimaoca != that.idPrimaoca) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPosiljaoca;
        result = 31 * result + idPrimaoca;
        return result;
    }
}
