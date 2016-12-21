package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
public class StoEntityPK implements Serializable {
    private int idRestorana;
    private int brojStola;

    @Column(name = "ID_RESTORANA")
    @Id
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Column(name = "BROJ_STOLA")
    @Id
    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoEntityPK that = (StoEntityPK) o;

        if (idRestorana != that.idRestorana) return false;
        if (brojStola != that.brojStola) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRestorana;
        result = 31 * result + brojStola;
        return result;
    }
}
