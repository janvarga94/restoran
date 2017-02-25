package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "ocena_konobara", schema = "restorani", catalog = "")
@IdClass(OcenaKonobaraEntityPK.class)
public class OcenaKonobaraEntity {
    private int ocena;
    private String konobarEmail;
    private String gostEmail;

    @Basic
    @Column(name = "OCENA")
    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Id
    @Column(name = "KONOBAR_EMAIL")
    public String getKonobarEmail() {
        return konobarEmail;
    }

    public void setKonobarEmail(String konobarEmail) {
        this.konobarEmail = konobarEmail;
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

        OcenaKonobaraEntity that = (OcenaKonobaraEntity) o;

        if (ocena != that.ocena) return false;
        if (konobarEmail != null ? !konobarEmail.equals(that.konobarEmail) : that.konobarEmail != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ocena;
        result = 31 * result + (konobarEmail != null ? konobarEmail.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
