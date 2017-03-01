package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Entity
@Table(name = "pice_u_porudzbini", schema = "restorani", catalog = "")
public class PiceUPorudzbiniEntity {
    private int idPorudzbinePica;
    private String nazivPica;
    private Integer idPorudzbine;
    private String sankerEmail;
    private Integer idRestorana;

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
    @Column(name = "ID_PORUDZBINE")
    public Integer getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(Integer idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Basic
    @Column(name = "SANKER_EMAIL")
    public String getSankerEmail() {
        return sankerEmail;
    }

    public void setSankerEmail(String sankerEmail) {
        this.sankerEmail = sankerEmail;
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

        PiceUPorudzbiniEntity that = (PiceUPorudzbiniEntity) o;

        if (idPorudzbinePica != that.idPorudzbinePica) return false;
        if (nazivPica != null ? !nazivPica.equals(that.nazivPica) : that.nazivPica != null) return false;
        if (idPorudzbine != null ? !idPorudzbine.equals(that.idPorudzbine) : that.idPorudzbine != null) return false;
        if (sankerEmail != null ? !sankerEmail.equals(that.sankerEmail) : that.sankerEmail != null) return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPorudzbinePica;
        result = 31 * result + (nazivPica != null ? nazivPica.hashCode() : 0);
        result = 31 * result + (idPorudzbine != null ? idPorudzbine.hashCode() : 0);
        result = 31 * result + (sankerEmail != null ? sankerEmail.hashCode() : 0);
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        return result;
    }
}
