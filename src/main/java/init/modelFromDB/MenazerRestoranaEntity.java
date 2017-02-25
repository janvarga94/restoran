package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by janva on 2/25/2017.
 */
@Entity
@Table(name = "menazer_restorana", schema = "restorani", catalog = "")
public class MenazerRestoranaEntity {
    private Integer idRestorana;
    private String email;

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
    }

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

        MenazerRestoranaEntity that = (MenazerRestoranaEntity) o;

        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRestorana != null ? idRestorana.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
