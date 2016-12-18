package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "ocena_jela", schema = "restorani", catalog = "")
public class OcenaJelaEntity {
    private Integer ocena;
    private int id;
    private String jelNaziv;
    private String naziv;
    private String jeloNaziv;

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
    @Column(name = "JEL_NAZIV")
    public String getJelNaziv() {
        return jelNaziv;
    }

    public void setJelNaziv(String jelNaziv) {
        this.jelNaziv = jelNaziv;
    }

    @Basic
    @Column(name = "NAZIV")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Basic
    @Column(name = "JELO_NAZIV")
    public String getJeloNaziv() {
        return jeloNaziv;
    }

    public void setJeloNaziv(String jeloNaziv) {
        this.jeloNaziv = jeloNaziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OcenaJelaEntity that = (OcenaJelaEntity) o;

        if (id != that.id) return false;
        if (ocena != null ? !ocena.equals(that.ocena) : that.ocena != null) return false;
        if (jelNaziv != null ? !jelNaziv.equals(that.jelNaziv) : that.jelNaziv != null) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (jeloNaziv != null ? !jeloNaziv.equals(that.jeloNaziv) : that.jeloNaziv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ocena != null ? ocena.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (jelNaziv != null ? jelNaziv.hashCode() : 0);
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        result = 31 * result + (jeloNaziv != null ? jeloNaziv.hashCode() : 0);
        return result;
    }
}
