package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "namirnica", schema = "restorani", catalog = "")
public class NamirnicaEntity {
    private String naziv;
    private String tekst;
    private int idNamirnice;

    @Basic
    @Column(name = "NAZIV")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Basic
    @Column(name = "TEKST")
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @Id
    @Column(name = "ID_NAMIRNICE")
    public int getIdNamirnice() {
        return idNamirnice;
    }

    public void setIdNamirnice(int idNamirnice) {
        this.idNamirnice = idNamirnice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamirnicaEntity that = (NamirnicaEntity) o;

        if (idNamirnice != that.idNamirnice) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (tekst != null ? !tekst.equals(that.tekst) : that.tekst != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = naziv != null ? naziv.hashCode() : 0;
        result = 31 * result + (tekst != null ? tekst.hashCode() : 0);
        result = 31 * result + idNamirnice;
        return result;
    }
}
