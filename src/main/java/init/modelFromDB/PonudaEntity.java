package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by janva on 2/25/2017.
 */
@Entity
@Table(name = "ponuda", schema = "restorani", catalog = "")
public class PonudaEntity {
    private String emailPonudjaca;
    private Double cena;
    private int idPotraznje;
    private Date rokIsporuke;
    private String garancija;
    private Byte prihvacenoOdMenadzera;
    private Byte ponudjacVideoDalJePonudaPrihvacenaIliOdbijena;

    @Basic
    @Column(name = "EMAIL_PONUDJACA")
    public String getEmailPonudjaca() {
        return emailPonudjaca;
    }

    public void setEmailPonudjaca(String emailPonudjaca) {
        this.emailPonudjaca = emailPonudjaca;
    }

    @Basic
    @Column(name = "CENA")
    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Id
    @Column(name = "ID_POTRAZNJE")
    public int getIdPotraznje() {
        return idPotraznje;
    }

    public void setIdPotraznje(int idPotraznje) {
        this.idPotraznje = idPotraznje;
    }

    @Basic
    @Column(name = "ROK_ISPORUKE")
    public Date getRokIsporuke() {
        return rokIsporuke;
    }

    public void setRokIsporuke(Date rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    @Basic
    @Column(name = "GARANCIJA")
    public String getGarancija() {
        return garancija;
    }

    public void setGarancija(String garancija) {
        this.garancija = garancija;
    }

    @Basic
    @Column(name = "PRIHVACENO_OD_MENADZERA")
    public Byte getPrihvacenoOdMenadzera() {
        return prihvacenoOdMenadzera;
    }

    public void setPrihvacenoOdMenadzera(Byte prihvacenoOdMenadzera) {
        this.prihvacenoOdMenadzera = prihvacenoOdMenadzera;
    }

    @Basic
    @Column(name = "PONUDJAC_VIDEO_DAL_JE_PONUDA_PRIHVACENA_ILI_ODBIJENA")
    public Byte getPonudjacVideoDalJePonudaPrihvacenaIliOdbijena() {
        return ponudjacVideoDalJePonudaPrihvacenaIliOdbijena;
    }

    public void setPonudjacVideoDalJePonudaPrihvacenaIliOdbijena(Byte ponudjacVideoDalJePonudaPrihvacenaIliOdbijena) {
        this.ponudjacVideoDalJePonudaPrihvacenaIliOdbijena = ponudjacVideoDalJePonudaPrihvacenaIliOdbijena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PonudaEntity that = (PonudaEntity) o;

        if (idPotraznje != that.idPotraznje) return false;
        if (emailPonudjaca != null ? !emailPonudjaca.equals(that.emailPonudjaca) : that.emailPonudjaca != null)
            return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;
        if (rokIsporuke != null ? !rokIsporuke.equals(that.rokIsporuke) : that.rokIsporuke != null) return false;
        if (garancija != null ? !garancija.equals(that.garancija) : that.garancija != null) return false;
        if (prihvacenoOdMenadzera != null ? !prihvacenoOdMenadzera.equals(that.prihvacenoOdMenadzera) : that.prihvacenoOdMenadzera != null)
            return false;
        if (ponudjacVideoDalJePonudaPrihvacenaIliOdbijena != null ? !ponudjacVideoDalJePonudaPrihvacenaIliOdbijena.equals(that.ponudjacVideoDalJePonudaPrihvacenaIliOdbijena) : that.ponudjacVideoDalJePonudaPrihvacenaIliOdbijena != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = emailPonudjaca != null ? emailPonudjaca.hashCode() : 0;
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        result = 31 * result + idPotraznje;
        result = 31 * result + (rokIsporuke != null ? rokIsporuke.hashCode() : 0);
        result = 31 * result + (garancija != null ? garancija.hashCode() : 0);
        result = 31 * result + (prihvacenoOdMenadzera != null ? prihvacenoOdMenadzera.hashCode() : 0);
        result = 31 * result + (ponudjacVideoDalJePonudaPrihvacenaIliOdbijena != null ? ponudjacVideoDalJePonudaPrihvacenaIliOdbijena.hashCode() : 0);
        return result;
    }
}
