package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/26/2017.
 */
@Entity
@Table(name = "ocena_restorana", schema = "restorani", catalog = "")
@IdClass(OcenaRestoranaEntityPK.class)
public class OcenaRestoranaEntity {
    private Integer ocena;
    private int idRestorana;
    private String gostEmail;

    @Basic
    @Column(name = "OCENA")
    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OcenaRestoranaEntity that = (OcenaRestoranaEntity) o;

        if (idRestorana != that.idRestorana) return false;
        if (ocena != null ? !ocena.equals(that.ocena) : that.ocena != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ocena != null ? ocena.hashCode() : 0;
        result = 31 * result + idRestorana;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
