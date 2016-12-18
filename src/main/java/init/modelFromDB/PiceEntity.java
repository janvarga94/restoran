package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "pice", schema = "restorani", catalog = "")
public class PiceEntity {
    private String nazivPica;
    private Integer idRestorana;
    private String tekst;
    private Double cena;

    @Id
    @Column(name = "NAZIV_PICA")
    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
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
    @Column(name = "TEKST")
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
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

        if (nazivPica != null ? !nazivPica.equals(that.nazivPica) : that.nazivPica != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (tekst != null ? !tekst.equals(that.tekst) : that.tekst != null) return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivPica != null ? nazivPica.hashCode() : 0;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (tekst != null ? tekst.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }
}