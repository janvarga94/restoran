package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Entity
@Table(name = "pice_u_porudzbini", schema = "restorani", catalog = "")
public class PiceUPorudzbiniEntity {
    private int idPorudzbinePica;
    private String nazivPica;
    private Date kreirana;
    private Integer idRestorana;
    private String gostEmail;
    private String email;
    private String sankerEmail;

    @Id
    @Column(name = "ID_PORUDZBINE_PICA")
    public int getIdPorudzbinePica() {
        return idPorudzbinePica;
    }

    public void setIdPorudzbinePica(int idPorudzbinePica) {
        this.idPorudzbinePica = idPorudzbinePica;
    }

    @Basic
    @Column(name = "NAZIV_PICA")
    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
    }

    @Basic
    @Column(name = "KREIRANA")
    public Date getKreirana() {
        return kreirana;
    }

    public void setKreirana(Date kreirana) {
        this.kreirana = kreirana;
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
    @Column(name = "GOST_EMAIL")
    public String getGostEmail() {
        return gostEmail;
    }

    public void setGostEmail(String gostEmail) {
        this.gostEmail = gostEmail;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
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

        PiceUPorudzbiniEntity that = (PiceUPorudzbiniEntity) o;

        if (idPorudzbinePica != that.idPorudzbinePica) return false;
        if (nazivPica != null ? !nazivPica.equals(that.nazivPica) : that.nazivPica != null) return false;
        if (kreirana != null ? !kreirana.equals(that.kreirana) : that.kreirana != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (sankerEmail != null ? !sankerEmail.equals(that.sankerEmail) : that.sankerEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPorudzbinePica;
        result = 31 * result + (nazivPica != null ? nazivPica.hashCode() : 0);
        result = 31 * result + (kreirana != null ? kreirana.hashCode() : 0);
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sankerEmail != null ? sankerEmail.hashCode() : 0);
        return result;
    }
}
