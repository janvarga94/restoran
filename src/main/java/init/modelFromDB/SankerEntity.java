package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Svetozar Stojkovic on 2/27/2017.
 */
@Entity
@Table(name = "sanker", schema = "restorani", catalog = "")
public class SankerEntity {
    private String sankerEmail;

    @Id
    @Column(name = "SANKER_EMAIL")
    public String getSankerEmail() {
        return sankerEmail;
    }

    public void setSankerEmail(String sankerEmail) {
        this.sankerEmail = sankerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SankerEntity that = (SankerEntity) o;

        if (sankerEmail != null ? !sankerEmail.equals(that.sankerEmail) : that.sankerEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sankerEmail != null ? sankerEmail.hashCode() : 0;
    }
}
