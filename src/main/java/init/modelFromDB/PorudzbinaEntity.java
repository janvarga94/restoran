package init.modelFromDB;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Svetozar Stojkovic on 2/26/2017.
 */
@Entity
@Table(name = "porudzbina", schema = "restorani", catalog = "")
public class PorudzbinaEntity {
    private Timestamp spremnoU;
    private Byte otkazanaOdStraneGosta;
    private Timestamp kreirana;
    private int idPorudzbine;
    private String konobarEmail;
    private int idRestorana;
    private String gostEmail;
    private Integer idRezervacije;
    private Timestamp privacenaOdKuvaraU;
    private Timestamp gostZeliSpremnoU;
    private Integer ukupnaCena;
    private Byte placeno;

    @Basic
    @Column(name = "SPREMNO_U")
    public Timestamp getSpremnoU() {
        return spremnoU;
    }

    public void setSpremnoU(Timestamp spremnoU) {
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

    @Basic
    @Column(name = "KREIRANA")
    public Timestamp getKreirana() {
        return kreirana;
    }

    public void setKreirana(Timestamp kreirana) {
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
    @Column(name = "KONOBAR_EMAIL")
    public String getKonobarEmail() {
        return konobarEmail;
    }

    public void setKonobarEmail(String konobarEmail) {
        this.konobarEmail = konobarEmail;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Basic
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

    @Basic
    @Column(name = "PRIVACENA_OD_KUVARA_U")
    public Timestamp getPrivacenaOdKuvaraU() {
        return privacenaOdKuvaraU;
    }

    public void setPrivacenaOdKuvaraU(Timestamp privacenaOdKuvaraU) {
        this.privacenaOdKuvaraU = privacenaOdKuvaraU;
    }

    @Basic
    @Column(name = "GOST_ZELI_SPREMNO_U")
    public Timestamp getGostZeliSpremnoU() {
        return gostZeliSpremnoU;
    }

    public void setGostZeliSpremnoU(Timestamp gostZeliSpremnoU) {
        this.gostZeliSpremnoU = gostZeliSpremnoU;
    }

    @Basic
    @Column(name = "UKUPNA_CENA")
    public Integer getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(Integer ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    @Basic
    @Column(name = "PLACENO")
    public Byte getPlaceno() {
        return placeno;
    }

    public void setPlaceno(Byte placeno) {
        this.placeno = placeno;
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
        if (konobarEmail != null ? !konobarEmail.equals(that.konobarEmail) : that.konobarEmail != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;
        if (idRezervacije != null ? !idRezervacije.equals(that.idRezervacije) : that.idRezervacije != null)
            return false;
        if (privacenaOdKuvaraU != null ? !privacenaOdKuvaraU.equals(that.privacenaOdKuvaraU) : that.privacenaOdKuvaraU != null)
            return false;
        if (gostZeliSpremnoU != null ? !gostZeliSpremnoU.equals(that.gostZeliSpremnoU) : that.gostZeliSpremnoU != null)
            return false;
        if (ukupnaCena != null ? !ukupnaCena.equals(that.ukupnaCena) : that.ukupnaCena != null) return false;
        if (placeno != null ? !placeno.equals(that.placeno) : that.placeno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spremnoU != null ? spremnoU.hashCode() : 0;
        result = 31 * result + (otkazanaOdStraneGosta != null ? otkazanaOdStraneGosta.hashCode() : 0);
        result = 31 * result + (kreirana != null ? kreirana.hashCode() : 0);
        result = 31 * result + idPorudzbine;
        result = 31 * result + (konobarEmail != null ? konobarEmail.hashCode() : 0);
        result = 31 * result + idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + (idRezervacije != null ? idRezervacije.hashCode() : 0);
        result = 31 * result + (privacenaOdKuvaraU != null ? privacenaOdKuvaraU.hashCode() : 0);
        result = 31 * result + (gostZeliSpremnoU != null ? gostZeliSpremnoU.hashCode() : 0);
        result = 31 * result + (ukupnaCena != null ? ukupnaCena.hashCode() : 0);
        result = 31 * result + (placeno != null ? placeno.hashCode() : 0);
        return result;
    }
}
