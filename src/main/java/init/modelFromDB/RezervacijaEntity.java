package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
@Entity
@Table(name = "rezervacija", schema = "restorani", catalog = "")
@IdClass(RezervacijaEntityPK.class)
public class RezervacijaEntity {
    private Date pocetak;
    private Date kraj;
    private int brojStola;
    private int idRestorana;
    private Byte otkazano;
    private String gostEmail;
    private int idRezervacije;

    @Basic
    @Id
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
    @Id
    @Column(name = "BROJ_STOLA")
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
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
    @Column(name = "OTKAZANO")
    public Byte getOtkazano() {
        return otkazano;
    }

    public void setOtkazano(Byte otkazano) {
        this.otkazano = otkazano;
    }

    @Basic
    @Id
    @Column(name = "GOST_EMAIL")
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RezervacijaEntity that = (RezervacijaEntity) o;

        if (brojStola != that.brojStola) return false;
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
        result = 31 * result + idRestorana;
        result = 31 * result + (otkazano != null ? otkazano.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "ID_REZERVACIJE")
    public int getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(int idRezervacije) {
        this.idRezervacije = idRezervacije;
    }
}
