package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "smena", schema = "restorani", catalog = "")
public class SmenaEntity {
    private Integer idRestorana;
    private Date pecetak;
    private int idSmene;
    private Integer brojSmene;

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Basic
    @Column(name = "PECETAK")
    public Date getPecetak() {
        return pecetak;
    }

    public void setPecetak(Date pecetak) {
        this.pecetak = pecetak;
    }

    @Id
    @Column(name = "ID_SMENE")
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Basic
    @Column(name = "BROJ_SMENE")
    public Integer getBrojSmene() {
        return brojSmene;
    }

    public void setBrojSmene(Integer brojSmene) {
        this.brojSmene = brojSmene;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmenaEntity that = (SmenaEntity) o;

        if (idSmene != that.idSmene) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (pecetak != null ? !pecetak.equals(that.pecetak) : that.pecetak != null) return false;
        if (brojSmene != null ? !brojSmene.equals(that.brojSmene) : that.brojSmene != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRestorana != null ? idRestorana.hashCode() : 0;
        result = 31 * result + (pecetak != null ? pecetak.hashCode() : 0);
        result = 31 * result + idSmene;
        result = 31 * result + (brojSmene != null ? brojSmene.hashCode() : 0);
        return result;
    }
}
