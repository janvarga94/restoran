package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
@Entity
@Table(name = "poziv_prijatelja", schema = "restorani", catalog = "")
@IdClass(PozivPrijateljaEntityPK.class)
public class PozivPrijateljaEntity {
    private Byte prihvacenPoziv;
    private String prijateljEmail;
    private Integer brojStola;
    private int idRestorana;
    private Date pocetak;
    private String gostEmail;

    @Basic
    @Column(name = "PRIHVACEN_POZIV")
    public Byte getPrihvacenPoziv() {
        return prihvacenPoziv;
    }

    public void setPrihvacenPoziv(Byte prihvacenPoziv) {
        this.prihvacenPoziv = prihvacenPoziv;
    }

    @Id
    @Column(name = "PRIJATELJ_EMAIL")
    public String getPrijateljEmail() {
        return prijateljEmail;
    }

    public void setPrijateljEmail(String prijateljEmail) {
        this.prijateljEmail = prijateljEmail;
    }

    @Basic
    @Column(name = "BROJ_STOLA")
    public Integer getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(Integer brojStola) {
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
    @Column(name = "POCETAK")
    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

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

        PozivPrijateljaEntity that = (PozivPrijateljaEntity) o;

        if (idRestorana != that.idRestorana) return false;
        if (prihvacenPoziv != null ? !prihvacenPoziv.equals(that.prihvacenPoziv) : that.prihvacenPoziv != null)
            return false;
        if (prijateljEmail != null ? !prijateljEmail.equals(that.prijateljEmail) : that.prijateljEmail != null)
            return false;
        if (brojStola != null ? !brojStola.equals(that.brojStola) : that.brojStola != null) return false;
        if (pocetak != null ? !pocetak.equals(that.pocetak) : that.pocetak != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prihvacenPoziv != null ? prihvacenPoziv.hashCode() : 0;
        result = 31 * result + (prijateljEmail != null ? prijateljEmail.hashCode() : 0);
        result = 31 * result + (brojStola != null ? brojStola.hashCode() : 0);
        result = 31 * result + idRestorana;
        result = 31 * result + (pocetak != null ? pocetak.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
