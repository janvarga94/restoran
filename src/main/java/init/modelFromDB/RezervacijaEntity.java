package init.modelFromDB;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "rezervacija", schema = "restorani", catalog = "")
public class RezervacijaEntity {
    private Timestamp pocetak;
    private Timestamp kraj;
    private int brojStola;
    private Byte otkazano;
    private String gostEmail;
    private int idRezervacije;
    private int idReona;
    private int idRestorana;

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
    @Column(name = "BROJ_STOLA")
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
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

    @Basic
    @Column(name = "ID_REONA")
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RezervacijaEntity that = (RezervacijaEntity) o;

        if (brojStola != that.brojStola) return false;
        if (idRezervacije != that.idRezervacije) return false;
        if (idReona != that.idReona) return false;
        if (idRestorana != that.idRestorana) return false;
        if (pocetak != null ? !pocetak.equals(that.pocetak) : that.pocetak != null) return false;
        if (kraj != null ? !kraj.equals(that.kraj) : that.kraj != null) return false;
        if (otkazano != null ? !otkazano.equals(that.otkazano) : that.otkazano != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pocetak != null ? pocetak.hashCode() : 0;
        result = 31 * result + (kraj != null ? kraj.hashCode() : 0);
        result = 31 * result + brojStola;
        result = 31 * result + (otkazano != null ? otkazano.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + idRezervacije;
        result = 31 * result + idReona;
        result = 31 * result + idRestorana;
        return result;
    }
}
