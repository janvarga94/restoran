package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Entity
@Table(name = "potraznja_pica", schema = "restorani", catalog = "")
@IdClass(PotraznjaPicaEntityPK.class)
public class PotraznjaPicaEntity {
    private String naziv;
    private int idPotraznje;
    private int idRestorana;

    @Id
    @Column(name = "NAZIV")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Id
    @Column(name = "ID_POTRAZNJE")
    public int getIdPotraznje() {
        return idPotraznje;
    }

    public void setIdPotraznje(int idPotraznje) {
        this.idPotraznje = idPotraznje;
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

        PotraznjaPicaEntity that = (PotraznjaPicaEntity) o;

        if (idPotraznje != that.idPotraznje) return false;
        if (idRestorana != that.idRestorana) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = naziv != null ? naziv.hashCode() : 0;
        result = 31 * result + idPotraznje;
        result = 31 * result + idRestorana;
        return result;
    }
}
