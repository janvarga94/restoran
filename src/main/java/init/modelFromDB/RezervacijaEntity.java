package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "rezervacija", schema = "restorani", catalog = "")
public class RezervacijaEntity {
    private Integer idStola;
    private Integer idGosta;
    private Date pocetak;
    private Date kraj;
    private Byte otkazana;
    private int idRezervacije;

    @Basic
    @Column(name = "ID_STOLA")
    public Integer getIdStola() {
        return idStola;
    }

    public void setIdStola(Integer idStola) {
        this.idStola = idStola;
    }

    @Basic
    @Column(name = "ID_GOSTA")
    public Integer getIdGosta() {
        return idGosta;
    }

    public void setIdGosta(Integer idGosta) {
        this.idGosta = idGosta;
    }

    @Basic
    @Column(name = "POCETAK")
    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    @Basic
    @Column(name = "KRAJ")
    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    @Basic
    @Column(name = "OTKAZANA")
    public Byte getOtkazana() {
        return otkazana;
    }

    public void setOtkazana(Byte otkazana) {
        this.otkazana = otkazana;
    }

    @Id
    @Column(name = "ID_REZERVACIJE")
    public int getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(int idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RezervacijaEntity that = (RezervacijaEntity) o;

        if (idRezervacije != that.idRezervacije) return false;
        if (idStola != null ? !idStola.equals(that.idStola) : that.idStola != null) return false;
        if (idGosta != null ? !idGosta.equals(that.idGosta) : that.idGosta != null) return false;
        if (pocetak != null ? !pocetak.equals(that.pocetak) : that.pocetak != null) return false;
        if (kraj != null ? !kraj.equals(that.kraj) : that.kraj != null) return false;
        if (otkazana != null ? !otkazana.equals(that.otkazana) : that.otkazana != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStola != null ? idStola.hashCode() : 0;
        result = 31 * result + (idGosta != null ? idGosta.hashCode() : 0);
        result = 31 * result + (pocetak != null ? pocetak.hashCode() : 0);
        result = 31 * result + (kraj != null ? kraj.hashCode() : 0);
        result = 31 * result + (otkazana != null ? otkazana.hashCode() : 0);
        result = 31 * result + idRezervacije;
        return result;
    }
}
