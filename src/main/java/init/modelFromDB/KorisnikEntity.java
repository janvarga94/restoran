package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
@Entity
@Table(name = "korisnik", schema = "restorani", catalog = "")
public class KorisnikEntity {
    private String email;
    private String lozinka;
    private String ime;
    private String prezime;

    @Id
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "IME")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Basic
    @Column(name = "PREZIME")
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KorisnikEntity that = (KorisnikEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lozinka != null ? !lozinka.equals(that.lozinka) : that.lozinka != null) return false;
        if (ime != null ? !ime.equals(that.ime) : that.ime != null) return false;
        if (prezime != null ? !prezime.equals(that.prezime) : that.prezime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (lozinka != null ? lozinka.hashCode() : 0);
        result = 31 * result + (ime != null ? ime.hashCode() : 0);
        result = 31 * result + (prezime != null ? prezime.hashCode() : 0);
        return result;
    }
}
