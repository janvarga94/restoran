package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "konobar", schema = "restorani", catalog = "")
public class KonobarEntity {
    private String konobarEmail;

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

        KonobarEntity that = (KonobarEntity) o;

        if (konobarEmail != null ? !konobarEmail.equals(that.konobarEmail) : that.konobarEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return konobarEmail != null ? konobarEmail.hashCode() : 0;
    }
}
