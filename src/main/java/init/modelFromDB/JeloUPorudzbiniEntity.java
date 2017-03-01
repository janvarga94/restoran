package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Entity
@Table(name = "jelo_u_porudzbini", schema = "restorani", catalog = "")
public class JeloUPorudzbiniEntity {
    private int idPorudzbineJela;
    private String nazivJela;
    private Integer idPorudzbine;
    private String kuvarEmail;
    private Integer idRestorana;

    @Id
    @Column(name = "ID_PORUDZBINE_JELA")
    public int getIdPorudzbineJela() {
        return idPorudzbineJela;
    }

    public void setIdPorudzbineJela(int idPorudzbineJela) {
        this.idPorudzbineJela = idPorudzbineJela;
    }

    @Basic
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Basic
    @Column(name = "ID_PORUDZBINE")
    public Integer getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(Integer idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Basic
    @Column(name = "KUVAR_EMAIL")
    public String getKuvarEmail() {
        return kuvarEmail;
    }

    public void setKuvarEmail(String kuvarEmail) {
        this.kuvarEmail = kuvarEmail;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeloUPorudzbiniEntity that = (JeloUPorudzbiniEntity) o;

        if (idPorudzbineJela != that.idPorudzbineJela) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (idPorudzbine != null ? !idPorudzbine.equals(that.idPorudzbine) : that.idPorudzbine != null) return false;
        if (kuvarEmail != null ? !kuvarEmail.equals(that.kuvarEmail) : that.kuvarEmail != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPorudzbineJela;
        result = 31 * result + (nazivJela != null ? nazivJela.hashCode() : 0);
        result = 31 * result + (idPorudzbine != null ? idPorudzbine.hashCode() : 0);
        result = 31 * result + (kuvarEmail != null ? kuvarEmail.hashCode() : 0);
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        return result;
    }
}
