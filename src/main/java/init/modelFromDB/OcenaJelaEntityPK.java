package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
public class OcenaJelaEntityPK implements Serializable {
    private String nazivJela;
    private String gostEmail;
    private int idRestorana;

    @Column(name = "NAZIV_JELA")
    @Id
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Column(name = "GOST_EMAIL")
    @Id
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
    }

    @Column(name = "ID_RESTORANA")
    @Id
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

        OcenaJelaEntityPK that = (OcenaJelaEntityPK) o;

        if (idRestorana != that.idRestorana) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivJela != null ? nazivJela.hashCode() : 0;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + idRestorana;
        return result;
    }
}
