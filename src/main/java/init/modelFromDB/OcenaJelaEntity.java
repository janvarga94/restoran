package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Entity
@Table(name = "ocena_jela", schema = "restorani", catalog = "")
@IdClass(OcenaJelaEntityPK.class)
public class OcenaJelaEntity {
    private Integer ocena;
    private String nazivJela;
    private String gostEmail;
    private int idRestorana;

    @Basic
    @Column(name = "OCENA")
    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    @Id
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Id
    @Column(name = "GOST_EMAIL")
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
    }

    @Id
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

        OcenaJelaEntity that = (OcenaJelaEntity) o;

        if (idRestorana != that.idRestorana) return false;
        if (ocena != null ? !ocena.equals(that.ocena) : that.ocena != null) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ocena != null ? ocena.hashCode() : 0;
        result = 31 * result + (nazivJela != null ? nazivJela.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + idRestorana;
        return result;
    }
}
