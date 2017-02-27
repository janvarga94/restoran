package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "jelo_u_porudzbini", schema = "restorani", catalog = "")
public class JeloUPorudzbiniEntity {
    private String nazivJela;
    private int idPorudzbineJela;
    private Date kreirana;
    private Integer idRestorana;
    private String gostEmail;
    private String email;
    private String kuvarEmail;

    @Basic
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Id
    @Column(name = "ID_PORUDZBINE_JELA")
    public int getIdPorudzbineJela() {
        return idPorudzbineJela;
    }

    public void setIdPorudzbineJela(int idPorudzbineJela) {
        this.idPorudzbineJela = idPorudzbineJela;
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
    @Column(name = "KUVAR_EMAIL")
    public String getKuvarEmail() {
        return kuvarEmail;
    }

    public void setKuvarEmail(String kuvarEmail) {
        this.kuvarEmail = kuvarEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeloUPorudzbiniEntity that = (JeloUPorudzbiniEntity) o;

        if (idPorudzbineJela != that.idPorudzbineJela) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (kreirana != null ? !kreirana.equals(that.kreirana) : that.kreirana != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (gostEmail != null ? !gostEmail.equals(that.gostEmail) : that.gostEmail != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (kuvarEmail != null ? !kuvarEmail.equals(that.kuvarEmail) : that.kuvarEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivJela != null ? nazivJela.hashCode() : 0;
        result = 31 * result + idPorudzbineJela;
        result = 31 * result + (kreirana != null ? kreirana.hashCode() : 0);
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (gostEmail != null ? gostEmail.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (kuvarEmail != null ? kuvarEmail.hashCode() : 0);
        return result;
    }
}
