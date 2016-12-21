package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
@Entity
@Table(name = "sto", schema = "restorani", catalog = "")
@IdClass(StoEntityPK.class)
public class StoEntity {
    private int idRestorana;
    private Integer idReona;
    private int brojStola;

    @Id
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Basic
    @Column(name = "ID_REONA")
    public Integer getIdReona() {
        return idReona;
    }

    public void setIdReona(Integer idReona) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoEntity stoEntity = (StoEntity) o;

        if (idRestorana != stoEntity.idRestorana) return false;
        if (brojStola != stoEntity.brojStola) return false;
        if (idReona != null ? !idReona.equals(stoEntity.idReona) : stoEntity.idReona != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRestorana;
        result = 31 * result + (idReona != null ? idReona.hashCode() : 0);
        result = 31 * result + brojStola;
        return result;
    }
}
