package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "porudzbina", schema = "restorani", catalog = "")
@IdClass(PorudzbinaEntityPK.class)
public class PorudzbinaEntity {
    private Date spremnoU;
    private Byte otkazanaOdStraneGosta;
    private Date kreirana;
    private int idPorudzbine;
    private Date privacenaOdKuvaraU;
    private Date gostZeliSpremnoU;
    private String konobarEmail;
    private int idRestorana;
    private String gostEmail;
    private Integer idRezervacije;

    @Basic
    @Column(name = "SPREMNO_U")
    public Date getSpremnoU() {
        return spremnoU;
    }

    public void setSpremnoU(Date spremnoU) {
        this.spremnoU = spremnoU;
    }

    @Basic
    @Column(name = "OTKAZANA_OD_STRANE_GOSTA")
    public Byte getOtkazanaOdStraneGosta() {
        return otkazanaOdStraneGosta;
    }

    public void setOtkazanaOdStraneGosta(Byte otkazanaOdStraneGosta) {
        this.otkazanaOdStraneGosta = otkazanaOdStraneGosta;
    }

    @Id
    @Column(name = "KREIRANA")
    public Date getKreirana() {
        return kreirana;
    }

    public void setKreirana(Date kreirana) {
        this.kreirana = kreirana;
    }

    @Basic
    @Column(name = "ID_PORUDZBINE")
    public int getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(int idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Basic
    @Column(name = "PRIVACENA_OD_KUVARA_U")
    public Date getPrivacenaOdKuvaraU() {
        return privacenaOdKuvaraU;
    }

    public void setPrivacenaOdKuvaraU(Date privacenaOdKuvaraU) {
        this.privacenaOdKuvaraU = privacenaOdKuvaraU;
    }

    @Basic
    @Column(name = "GOST_ZELI_SPREMNO_U")
    public Date getGostZeliSpremnoU() {
        return gostZeliSpremnoU;
    }

    public void setGostZeliSpremnoU(Date gostZeliSpremnoU) {
        this.gostZeliSpremnoU = gostZeliSpremnoU;
    }

    @Basic
    @Column(name = "KONOBAR_EMAIL")
    public String getKonobarEmail() {
        return konobarEmail;
    }

    public void setKonobarEmail(String konobarEmail) {
        this.konobarEmail = konobarEmail;
    }

    @Id
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Id
    @Column(name = "GOST_EMAIL")
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
    }

    @Basic
    @Column(name = "ID_REZERVACIJE")
    public Integer getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(Integer idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PorudzbinaEntity that = (PorudzbinaEntity) o;

        if (idPorudzbine != that.idPorudzbine) return false;
        if (idRestorana != that.idRestorana) return false;
        if (spremnoU != null ? !spremnoU.equals(that.spremnoU) : that.spremnoU != null) return false;
        if (otkazanaOdStraneGosta != null ? !otkazanaOdStraneGosta.equals(that.otkazanaOdStraneGosta) : that.otkazanaOdStraneGosta != null)
            return false;
        if (kreirana != null ? !kreirana.equals(that.kreirana) : that.kreirana != null) return false;
        if (privacenaOdKuvaraU != null ? !privacenaOdKuvaraU.equals(that.privacenaOdKuvaraU) : that.privacenaOdKuvaraU != null)
            return false;
        if (gostZeliSpremnoU != null ? !gostZeliSpremnoU.equals(that.gostZeliSpremnoU) : that.gostZeliSpremnoU != null)
            return false;
        if (konobarEmail != null ? !konobarEmail.equals(that.konobarEmail) : that.konobarEmail != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;
        if (idRezervacije != null ? !idRezervacije.equals(that.idRezervacije) : that.idRezervacije != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spremnoU != null ? spremnoU.hashCode() : 0;
        result = 31 * result + (otkazanaOdStraneGosta != null ? otkazanaOdStraneGosta.hashCode() : 0);
        result = 31 * result + (kreirana != null ? kreirana.hashCode() : 0);
        result = 31 * result + idPorudzbine;
        result = 31 * result + (privacenaOdKuvaraU != null ? privacenaOdKuvaraU.hashCode() : 0);
        result = 31 * result + (gostZeliSpremnoU != null ? gostZeliSpremnoU.hashCode() : 0);
        result = 31 * result + (konobarEmail != null ? konobarEmail.hashCode() : 0);
        result = 31 * result + idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + (idRezervacije != null ? idRezervacije.hashCode() : 0);
        return result;
    }
}
