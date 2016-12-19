package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "ocena_restorana", schema = "restorani", catalog = "")
public class OcenaRestoranaEntity {
    private String nazivRestorana;
    private Integer ocena;
    private int id;
    private Integer idRestorana;

    @Basic
    @Column(name = "NAZIV_RESTORANA")
    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    @Basic
    @Column(name = "OCENA")
    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        OcenaRestoranaEntity that = (OcenaRestoranaEntity) o;

        if (id != that.id) return false;
        if (nazivRestorana != null ? !nazivRestorana.equals(that.nazivRestorana) : that.nazivRestorana != null)
            return false;
        if (ocena != null ? !ocena.equals(that.ocena) : that.ocena != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivRestorana != null ? nazivRestorana.hashCode() : 0;
        result = 31 * result + (ocena != null ? ocena.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        return result;
    }
}
