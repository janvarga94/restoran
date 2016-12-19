package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "zahtev_za_prijateljstvo", schema = "restorani", catalog = "")
@IdClass(ZahtevZaPrijateljstvoEntityPK.class)
public class ZahtevZaPrijateljstvoEntity {
    private int idPosiljaoca;
    private int idPrimaoca;

    @Id
    @Column(name = "ID_POSILJAOCA")
    public int getIdPosiljaoca() {
        return idPosiljaoca;
    }

    public void setIdPosiljaoca(int idPosiljaoca) {
        this.idPosiljaoca = idPosiljaoca;
    }

    @Id
    @Column(name = "ID_PRIMAOCA")
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

        ZahtevZaPrijateljstvoEntity that = (ZahtevZaPrijateljstvoEntity) o;

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
