package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "reon", schema = "restorani", catalog = "")
@IdClass(ReonEntityPK.class)
public class ReonEntity {
    private int idReona;
    private int idRestorana;
    private String opis;

    @Id
    @Column(name = "ID_REONA")
    public int getIdReona() {
        return idReona;
    }

    public void setIdReona(int idReona) {
        this.idReona = idReona;
    }

    @Id
    @Column(name = "ID_RESTORANA")
    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Basic
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReonEntity that = (ReonEntity) o;

        if (idReona != that.idReona) return false;
        if (idRestorana != that.idRestorana) return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReona;
        result = 31 * result + idRestorana;
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        return result;
    }
}
