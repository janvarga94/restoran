package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "gost", schema = "restorani", catalog = "")
public class GostEntity {
    private String email;
    private Integer idRestorana;
    private String ime;
    private String prezime;
    private String lozinka;
    private Byte aktiviran;
    private int idGosta;

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
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

    @Basic
    @Column(name = "LOZINKA")
    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Basic
    @Column(name = "AKTIVIRAN")
    public Byte getAktiviran() {
        return aktiviran;
    }

    public void setAktiviran(Byte aktiviran) {
        this.aktiviran = aktiviran;
    }

    @Id
    @Column(name = "ID_GOSTA")
    public int getIdGosta() {
        return idGosta;
    }

    public void setIdGosta(int idGosta) {
        this.idGosta = idGosta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GostEntity that = (GostEntity) o;

        if (idGosta != that.idGosta) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (ime != null ? !ime.equals(that.ime) : that.ime != null) return false;
        if (prezime != null ? !prezime.equals(that.prezime) : that.prezime != null) return false;
        if (lozinka != null ? !lozinka.equals(that.lozinka) : that.lozinka != null) return false;
        if (aktiviran != null ? !aktiviran.equals(that.aktiviran) : that.aktiviran != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (ime != null ? ime.hashCode() : 0);
        result = 31 * result + (prezime != null ? prezime.hashCode() : 0);
        result = 31 * result + (lozinka != null ? lozinka.hashCode() : 0);
        result = 31 * result + (aktiviran != null ? aktiviran.hashCode() : 0);
        result = 31 * result + idGosta;
        return result;
    }
}
