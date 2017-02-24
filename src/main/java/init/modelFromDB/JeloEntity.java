package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "jelo", schema = "restorani", catalog = "")
public class JeloEntity {
    private String nazivJela;
    private Integer idRestorana;
    private Integer idTipaJela;
    private String opis;
    private Double cena;

    @Id
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
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
    @Column(name = "ID_TIPA_JELA")
    public Integer getIdTipaJela() {
        return idTipaJela;
    }

    public void setIdTipaJela(Integer idTipaJela) {
        this.idTipaJela = idTipaJela;
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

        JeloEntity that = (JeloEntity) o;

        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (idTipaJela != null ? !idTipaJela.equals(that.idTipaJela) : that.idTipaJela != null) return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivJela != null ? nazivJela.hashCode() : 0;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (idTipaJela != null ? idTipaJela.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }
}
