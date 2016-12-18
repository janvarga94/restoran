package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "sto", schema = "restorani", catalog = "")
public class StoEntity {
    private int idStola;
    private Integer idRestorana;
    private Integer idReona;
    private Integer idPozicije;

    @Id
    @Column(name = "ID_STOLA")
    public int getIdStola() {
        return idStola;
    }

    public void setIdStola(int idStola) {
        this.idStola = idStola;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
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

    @Basic
    @Column(name = "ID_POZICIJE")
    public Integer getIdPozicije() {
        return idPozicije;
    }

    public void setIdPozicije(Integer idPozicije) {
        this.idPozicije = idPozicije;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoEntity stoEntity = (StoEntity) o;

        if (idStola != stoEntity.idStola) return false;
        if (idRestorana != null ? !idRestorana.equals(stoEntity.idRestorana) : stoEntity.idRestorana != null)
            return false;
        if (idReona != null ? !idReona.equals(stoEntity.idReona) : stoEntity.idReona != null) return false;
        if (idPozicije != null ? !idPozicije.equals(stoEntity.idPozicije) : stoEntity.idPozicije != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStola;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (idReona != null ? idReona.hashCode() : 0);
        result = 31 * result + (idPozicije != null ? idPozicije.hashCode() : 0);
        return result;
    }
}
