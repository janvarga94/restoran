package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "sto", schema = "restorani", catalog = "")
@IdClass(StoEntityPK.class)
public class StoEntity {
    private int idReona;
    private int brojStola;
    private int idRestorana;

    @Basic
    @Id
    @Column(name = "ID_REONA")
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Id
    @Column(name = "BROJ_STOLA")
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
    }

    @Basic
    @Id
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

        if (idReona != stoEntity.idReona) return false;
        if (brojStola != stoEntity.brojStola) return false;
        if (idRestorana != stoEntity.idRestorana) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReona;
        result = 31 * result + brojStola;
        result = 31 * result + idRestorana;
        return result;
    }
}
