package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "reon_u_smeni", schema = "restorani", catalog = "")
@IdClass(ReonUSmeniEntityPK.class)
public class ReonUSmeniEntity {
    private int idSmene;
    private int idReona;
    private int idRestorana;
    private String konobarEmail;

    @Id
    @Column(name = "ID_SMENE")
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Id
    @Column(name = "ID_REONA")
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
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
    @Column(name = "KONOBAR_EMAIL")
    public String getKonobarEmail() {
        return konobarEmail;
    }

    public void setKonobarEmail(String konobarEmail) {
        this.konobarEmail = konobarEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReonUSmeniEntity that = (ReonUSmeniEntity) o;

        if (idSmene != that.idSmene) return false;
        if (idReona != that.idReona) return false;
        if (idRestorana != that.idRestorana) return false;
        if (konobarEmail != null ? !konobarEmail.equals(that.konobarEmail) : that.konobarEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSmene;
        result = 31 * result + idReona;
        result = 31 * result + idRestorana;
        result = 31 * result + (konobarEmail != null ? konobarEmail.hashCode() : 0);
        return result;
    }
}
