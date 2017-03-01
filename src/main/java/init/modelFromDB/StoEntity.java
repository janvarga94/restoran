package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Entity
@Table(name = "sto", schema = "restorani", catalog = "")
public class StoEntity {
    private int brojStola;
    private int idReona;
    private int idRestorana;

    @Id
    @Column(name = "BROJ_STOLA")
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
    }

    @Basic
    @Column(name = "ID_REONA")
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
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

        StoEntity stoEntity = (StoEntity) o;

        if (brojStola != stoEntity.brojStola) return false;
        if (idReona != stoEntity.idReona) return false;
        if (idRestorana != stoEntity.idRestorana) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brojStola;
        result = 31 * result + idReona;
        result = 31 * result + idRestorana;
        return result;
    }
}
