package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "gost", schema = "restorani", catalog = "")
public class GostEntity {
    private Byte aktiviran;
    private String gostEmail;

    @Basic
    @Column(name = "AKTIVIRAN")
    public Byte getAktiviran() {
        return aktiviran;
    }

    public void setAktiviran(Byte aktiviran) {
        this.aktiviran = aktiviran;
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

        GostEntity that = (GostEntity) o;

        if (aktiviran != null ? !aktiviran.equals(that.aktiviran) : that.aktiviran != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aktiviran != null ? aktiviran.hashCode() : 0;
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        return result;
    }
}
