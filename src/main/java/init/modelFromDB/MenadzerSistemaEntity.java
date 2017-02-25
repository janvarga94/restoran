package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "menadzer_sistema", schema = "restorani", catalog = "")
public class MenadzerSistemaEntity {
    private String email;

    @Id
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenadzerSistemaEntity that = (MenadzerSistemaEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
