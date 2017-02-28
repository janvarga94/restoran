package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "pice", schema = "restorani", catalog = "")
@IdClass(PiceEntityPK.class)
public class PiceEntity {
    private String nazivPica;
    private int idRestorana;
    private String opis;
    private Double cena;

    @Id
    @Column(name = "NAZIV_PICA")
    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
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
    @Column(name = "CENA")
    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PiceEntity that = (PiceEntity) o;

        if (idRestorana != that.idRestorana) return false;
        if (nazivPica != null ? !nazivPica.equals(that.nazivPica) : that.nazivPica != null) return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivPica != null ? nazivPica.hashCode() : 0;
        result = 31 * result + idRestorana;
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }
}
