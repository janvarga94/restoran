package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/26/2017.
 */
@Entity
@Table(name = "tip_jela", schema = "restorani", catalog = "")
public class TipJelaEntity {
    private String opis;
    private int idTipaJela;

    @Basic
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Id
    @Column(name = "ID_TIPA_JELA")
    public int getIdTipaJela() {
        return idTipaJela;
    }

    public void setIdTipaJela(int idTipaJela) {
        this.idTipaJela = idTipaJela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipJelaEntity that = (TipJelaEntity) o;

        if (idTipaJela != that.idTipaJela) return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = opis != null ? opis.hashCode() : 0;
        result = 31 * result + idTipaJela;
        return result;
    }
}
