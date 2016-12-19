package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "menadzer_sistema", schema = "restorani", catalog = "")
public class MenadzerSistemaEntity {
    private String naziv;
    private String lozinka;
    private String email;

    @Id
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

    @Basic
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

        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (lozinka != null ? !lozinka.equals(that.lozinka) : that.lozinka != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = naziv != null ? naziv.hashCode() : 0;
        result = 31 * result + (lozinka != null ? lozinka.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
