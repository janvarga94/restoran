package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
public class PiceEntityPK implements Serializable {
    private String nazivPica;
    private int idRestorana;

    @Column(name = "NAZIV_PICA")
    @Id
    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
    }

    @Column(name = "ID_RESTORANA")
    @Id
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

        PiceEntityPK that = (PiceEntityPK) o;

        if (idRestorana != that.idRestorana) return false;
        if (nazivPica != null ? !nazivPica.equals(that.nazivPica) : that.nazivPica != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivPica != null ? nazivPica.hashCode() : 0;
        result = 31 * result + idRestorana;
        return result;
    }
}
