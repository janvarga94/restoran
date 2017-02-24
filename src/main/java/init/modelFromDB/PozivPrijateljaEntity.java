package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "poziv_prijatelja", schema = "restorani", catalog = "")
public class PozivPrijateljaEntity {
    private Byte prihvacenPoziv;
    private Date pocetak;
    private String prviEmail;
    private String drugiEmail;
    private Integer idRezervacije;
    private Byte posiljaocVideoOdgovor;
    private int idPoziva;

    @Basic
    @Column(name = "PRIHVACEN_POZIV")
    public Byte getPrihvacenPoziv() {
        return prihvacenPoziv;
    }

    public void setPrihvacenPoziv(Byte prihvacenPoziv) {
        this.prihvacenPoziv = prihvacenPoziv;
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
    @Column(name = "PRVI_EMAIL")
    public String getPrviEmail() {
        return prviEmail;
    }

    public void setPrviEmail(String prviEmail) {
        this.prviEmail = prviEmail;
    }

    @Basic
    @Column(name = "DRUGI_EMAIL")
    public String getDrugiEmail() {
        return drugiEmail;
    }

    public void setDrugiEmail(String drugiEmail) {
        this.drugiEmail = drugiEmail;
    }

    @Basic
    @Column(name = "ID_REZERVACIJE")
    public Integer getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(Integer idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    @Basic
    @Column(name = "POSILJAOC_VIDEO_ODGOVOR")
    public Byte getPosiljaocVideoOdgovor() {
        return posiljaocVideoOdgovor;
    }

    public void setPosiljaocVideoOdgovor(Byte posiljaocVideoOdgovor) {
        this.posiljaocVideoOdgovor = posiljaocVideoOdgovor;
    }

    @Id
    @Column(name = "ID_POZIVA")
    public int getIdPoziva() {
        return idPoziva;
    }

    public void setIdPoziva(int idPoziva) {
        this.idPoziva = idPoziva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PozivPrijateljaEntity that = (PozivPrijateljaEntity) o;

        if (idPoziva != that.idPoziva) return false;
        if (prihvacenPoziv != null ? !prihvacenPoziv.equals(that.prihvacenPoziv) : that.prihvacenPoziv != null)
            return false;
        if (pocetak != null ? !pocetak.equals(that.pocetak) : that.pocetak != null) return false;
        if (prviEmail != null ? !prviEmail.equals(that.prviEmail) : that.prviEmail != null) return false;
        if (drugiEmail != null ? !drugiEmail.equals(that.drugiEmail) : that.drugiEmail != null) return false;
        if (idRezervacije != null ? !idRezervacije.equals(that.idRezervacije) : that.idRezervacije != null)
            return false;
        if (posiljaocVideoOdgovor != null ? !posiljaocVideoOdgovor.equals(that.posiljaocVideoOdgovor) : that.posiljaocVideoOdgovor != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prihvacenPoziv != null ? prihvacenPoziv.hashCode() : 0;
        result = 31 * result + (pocetak != null ? pocetak.hashCode() : 0);
        result = 31 * result + (prviEmail != null ? prviEmail.hashCode() : 0);
        result = 31 * result + (drugiEmail != null ? drugiEmail.hashCode() : 0);
        result = 31 * result + (idRezervacije != null ? idRezervacije.hashCode() : 0);
        result = 31 * result + (posiljaocVideoOdgovor != null ? posiljaocVideoOdgovor.hashCode() : 0);
        result = 31 * result + idPoziva;
        return result;
    }
}
