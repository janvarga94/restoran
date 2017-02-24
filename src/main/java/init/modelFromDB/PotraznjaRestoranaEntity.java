package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "potraznja_restorana", schema = "restorani", catalog = "")
public class PotraznjaRestoranaEntity {
    private Date pocetakPonude;
    private Date krajPonude;
    private int idPotraznje;
    private Integer idRestorana;

    @Basic
    @Column(name = "POCETAK_PONUDE")
    public Date getPocetakPonude() {
        return pocetakPonude;
    }

    public void setPocetakPonude(Date pocetakPonude) {
        this.pocetakPonude = pocetakPonude;
    }

    @Basic
    @Column(name = "KRAJ_PONUDE")
    public Date getKrajPonude() {
        return krajPonude;
    }

    public void setKrajPonude(Date krajPonude) {
        this.krajPonude = krajPonude;
    }

    @Id
    @Column(name = "ID_POTRAZNJE")
    public int getIdPotraznje() {
        return idPotraznje;
    }

    public void setIdPotraznje(int idPotraznje) {
        this.idPotraznje = idPotraznje;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PotraznjaRestoranaEntity that = (PotraznjaRestoranaEntity) o;

        if (idPotraznje != that.idPotraznje) return false;
        if (pocetakPonude != null ? !pocetakPonude.equals(that.pocetakPonude) : that.pocetakPonude != null)
            return false;
        if (krajPonude != null ? !krajPonude.equals(that.krajPonude) : that.krajPonude != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pocetakPonude != null ? pocetakPonude.hashCode() : 0;
        result = 31 * result + (krajPonude != null ? krajPonude.hashCode() : 0);
        result = 31 * result + idPotraznje;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        return result;
    }
}
