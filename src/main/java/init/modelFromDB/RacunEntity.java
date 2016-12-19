package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "racun", schema = "restorani", catalog = "")
public class RacunEntity {
    private int idPorudzbine;

    @Id
    @Column(name = "ID_PORUDZBINE")
    public int getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(int idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RacunEntity that = (RacunEntity) o;

        if (idPorudzbine != that.idPorudzbine) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idPorudzbine;
    }
}
