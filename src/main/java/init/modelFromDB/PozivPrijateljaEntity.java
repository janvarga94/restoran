package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "poziv_prijatelja", schema = "restorani", catalog = "")
public class PozivPrijateljaEntity {
    private Integer idPozivaoca;
    private Integer idPozvanog;
    private Integer idRezervacije;
    private Byte prihvacenPoziv;
    private String token;

    @Basic
    @Column(name = "ID_POZIVAOCA")
    public Integer getIdPozivaoca() {
        return idPozivaoca;
    }

    public void setIdPozivaoca(Integer idPozivaoca) {
        this.idPozivaoca = idPozivaoca;
    }

    @Basic
    @Column(name = "ID_POZVANOG")
    public Integer getIdPozvanog() {
        return idPozvanog;
    }

    public void setIdPozvanog(Integer idPozvanog) {
        this.idPozvanog = idPozvanog;
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
    @Column(name = "PRIHVACEN_POZIV")
    public Byte getPrihvacenPoziv() {
        return prihvacenPoziv;
    }

    public void setPrihvacenPoziv(Byte prihvacenPoziv) {
        this.prihvacenPoziv = prihvacenPoziv;
    }

    @Id
    @Column(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PozivPrijateljaEntity that = (PozivPrijateljaEntity) o;

        if (idPozivaoca != null ? !idPozivaoca.equals(that.idPozivaoca) : that.idPozivaoca != null) return false;
        if (idPozvanog != null ? !idPozvanog.equals(that.idPozvanog) : that.idPozvanog != null) return false;
        if (idRezervacije != null ? !idRezervacije.equals(that.idRezervacije) : that.idRezervacije != null)
            return false;
        if (prihvacenPoziv != null ? !prihvacenPoziv.equals(that.prihvacenPoziv) : that.prihvacenPoziv != null)
            return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPozivaoca != null ? idPozivaoca.hashCode() : 0;
        result = 31 * result + (idPozvanog != null ? idPozvanog.hashCode() : 0);
        result = 31 * result + (idRezervacije != null ? idRezervacije.hashCode() : 0);
        result = 31 * result + (prihvacenPoziv != null ? prihvacenPoziv.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
