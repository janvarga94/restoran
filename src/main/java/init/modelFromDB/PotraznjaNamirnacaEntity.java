package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Entity
@Table(name = "potraznja_namirnaca", schema = "restorani", catalog = "")
@IdClass(PotraznjaNamirnacaEntityPK.class)
public class PotraznjaNamirnacaEntity {
    private int idNamirnice;
    private int idPotraznje;
    private Date dokad;
    @Id
    @Column(name = "ID_NAMIRNICE")
    public int getIdNamirnice() {
        return idNamirnice;
    }

    public void setIdNamirnice(int idNamirnice) {
        this.idNamirnice = idNamirnice;
    }

    @Id
    @Column(name = "ID_POTRAZNJE")
    public int getIdPotraznje() {
        return idPotraznje;
    }

    public void setIdPotraznje(int idPotraznje) {
        this.idPotraznje = idPotraznje;
    }

    @Basic
    @Column(name = "DOKAD")
    public Date getDokad() {
        return dokad;
    }

    public void setDokad(Date dokad) {
        this.dokad = dokad;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PotraznjaNamirnacaEntity that = (PotraznjaNamirnacaEntity) o;

        if (idNamirnice != that.idNamirnice) return false;
        if (idPotraznje != that.idPotraznje) return false;
        if (dokad != null ? !dokad.equals(that.dokad) : that.dokad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNamirnice;
        result = 31 * result + idPotraznje;
        result = 31 * result + (dokad != null ? dokad.hashCode() : 0);
        return result;
    }
}
