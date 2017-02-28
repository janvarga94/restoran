package init.modelFromDB;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "rezervacija", schema = "restorani", catalog = "")
public class RezervacijaEntity {
    private int brojStola;
    private Timestamp pocetak;
    private Timestamp kraj;
    private Byte otkazano;
    private String gostEmail;
    private int idRezervacije;

    @Basic
    @Column(name = "BROJ_STOLA")
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
    }

    @Basic
    @Column(name = "POCETAK")
    public Timestamp getPocetak() {
        return pocetak;
    }

    public void setPocetak(Timestamp pocetak) {
        this.pocetak = pocetak;
    }

    @Basic
    @Column(name = "KRAJ")
    public Timestamp getKraj() {
        return kraj;
    }

    public void setKraj(Timestamp kraj) {
        this.kraj = kraj;
    }

    @Basic
    @Column(name = "OTKAZANO")
    public Byte getOtkazano() {
        return otkazano;
    }

    public void setOtkazano(Byte otkazano) {
        this.otkazano = otkazano;
    }

    @Basic
    @Column(name = "GOST_EMAIL")
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
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

        if (brojStola != that.brojStola) return false;
        if (idRezervacije != that.idRezervacije) return false;
        if (pocetak != null ? !pocetak.equals(that.pocetak) : that.pocetak != null) return false;
        if (kraj != null ? !kraj.equals(that.kraj) : that.kraj != null) return false;
        if (otkazano != null ? !otkazano.equals(that.otkazano) : that.otkazano != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brojStola;
        result = 31 * result + (pocetak != null ? pocetak.hashCode() : 0);
        result = 31 * result + (kraj != null ? kraj.hashCode() : 0);
        result = 31 * result + (otkazano != null ? otkazano.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + idRezervacije;
        return result;
    }
}
