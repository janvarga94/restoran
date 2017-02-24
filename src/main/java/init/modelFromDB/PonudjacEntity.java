package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "ponudjac", schema = "restorani", catalog = "")
public class PonudjacEntity {
    private String email;
    private String naziv;
    private String lozinka;

    @Id
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "NAZIV")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Basic
    @Column(name = "LOZINKA")
    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PonudjacEntity that = (PonudjacEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (lozinka != null ? !lozinka.equals(that.lozinka) : that.lozinka != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        result = 31 * result + (lozinka != null ? lozinka.hashCode() : 0);
        return result;
    }
}
