package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "restoran", schema = "restorani", catalog = "")
public class RestoranEntity {
    private String vrsta;
    private String naziv;
    private int idRestorana;
    private String opis;
    private String adresa;

    @Basic
    @Column(name = "VRSTA")
    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    @Basic
    @Column(name = "NAZIV")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Id
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Basic
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    @Column(name = "ADRESA")
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestoranEntity that = (RestoranEntity) o;

        if (idRestorana != that.idRestorana) return false;
        if (vrsta != null ? !vrsta.equals(that.vrsta) : that.vrsta != null) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;
        if (adresa != null ? !adresa.equals(that.adresa) : that.adresa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vrsta != null ? vrsta.hashCode() : 0;
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        result = 31 * result + idRestorana;
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (adresa != null ? adresa.hashCode() : 0);
        return result;
    }
}
