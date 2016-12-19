package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "porudzbina", schema = "restorani", catalog = "")
public class PorudzbinaEntity {
    private Date spremnoU;
    private Integer mbrRadnika;
    private Integer idGosta;
    private Integer idRezervacije;
    private Byte otkazanaOdStraneGosta;
    private Date kreirana;
    private int idPorudzbine;
    private Date privacenaOdKuvaraU;
    private Date gostZeliSpremnoU;

    @Basic
    @Column(name = "SPREMNO_U")
    public Date getSpremnoU() {
        return spremnoU;
    }

    public void setSpremnoU(Date spremnoU) {
        this.spremnoU = spremnoU;
    }

    @Basic
    @Column(name = "MBR_RADNIKA")
    public Integer getMbrRadnika() {
        return mbrRadnika;
    }

    public void setMbrRadnika(Integer mbrRadnika) {
        this.mbrRadnika = mbrRadnika;
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
    @Column(name = "ID_REZERVACIJE")
    public Integer getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(Integer idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    @Basic
    @Column(name = "OTKAZANA_OD_STRANE_GOSTA")
    public Byte getOtkazanaOdStraneGosta() {
        return otkazanaOdStraneGosta;
    }

    public void setOtkazanaOdStraneGosta(Byte otkazanaOdStraneGosta) {
        this.otkazanaOdStraneGosta = otkazanaOdStraneGosta;
    }

    @Basic
    @Column(name = "KREIRANA")
    public Date getKreirana() {
        return kreirana;
    }

    public void setKreirana(Date kreirana) {
        this.kreirana = kreirana;
    }

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PorudzbinaEntity that = (PorudzbinaEntity) o;

        if (idPorudzbine != that.idPorudzbine) return false;
        if (spremnoU != null ? !spremnoU.equals(that.spremnoU) : that.spremnoU != null) return false;
        if (mbrRadnika != null ? !mbrRadnika.equals(that.mbrRadnika) : that.mbrRadnika != null) return false;
        if (idGosta != null ? !idGosta.equals(that.idGosta) : that.idGosta != null) return false;
        if (idRezervacije != null ? !idRezervacije.equals(that.idRezervacije) : that.idRezervacije != null)
            return false;
        if (otkazanaOdStraneGosta != null ? !otkazanaOdStraneGosta.equals(that.otkazanaOdStraneGosta) : that.otkazanaOdStraneGosta != null)
            return false;
        if (kreirana != null ? !kreirana.equals(that.kreirana) : that.kreirana != null) return false;
        if (privacenaOdKuvaraU != null ? !privacenaOdKuvaraU.equals(that.privacenaOdKuvaraU) : that.privacenaOdKuvaraU != null)
            return false;
        if (gostZeliSpremnoU != null ? !gostZeliSpremnoU.equals(that.gostZeliSpremnoU) : that.gostZeliSpremnoU != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spremnoU != null ? spremnoU.hashCode() : 0;
        result = 31 * result + (mbrRadnika != null ? mbrRadnika.hashCode() : 0);
        result = 31 * result + (idGosta != null ? idGosta.hashCode() : 0);
        result = 31 * result + (idRezervacije != null ? idRezervacije.hashCode() : 0);
        result = 31 * result + (otkazanaOdStraneGosta != null ? otkazanaOdStraneGosta.hashCode() : 0);
        result = 31 * result + (kreirana != null ? kreirana.hashCode() : 0);
        result = 31 * result + idPorudzbine;
        result = 31 * result + (privacenaOdKuvaraU != null ? privacenaOdKuvaraU.hashCode() : 0);
        result = 31 * result + (gostZeliSpremnoU != null ? gostZeliSpremnoU.hashCode() : 0);
        return result;
    }
}
