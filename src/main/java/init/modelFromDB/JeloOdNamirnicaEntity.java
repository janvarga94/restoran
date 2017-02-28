package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "jelo_od_namirnica", schema = "restorani", catalog = "")
@IdClass(JeloOdNamirnicaEntityPK.class)
public class JeloOdNamirnicaEntity {
    private int idNamirnice;
    private String nazivJela;
    private int idRestorana;

    @Basic
    @Column(name = "ID_NAMIRNICE")
    public int getIdNamirnice() {
        return idNamirnice;
    }

    public void setIdNamirnice(int idNamirnice) {
        this.idNamirnice = idNamirnice;
    }

    @Id
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Id
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeloOdNamirnicaEntity that = (JeloOdNamirnicaEntity) o;

        if (idNamirnice != that.idNamirnice) return false;
        if (idRestorana != that.idRestorana) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNamirnice;
        result = 31 * result + (nazivJela != null ? nazivJela.hashCode() : 0);
        result = 31 * result + idRestorana;
        return result;
    }
}
