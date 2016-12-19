package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "ocena_konobara", schema = "restorani", catalog = "")
@IdClass(OcenaKonobaraEntityPK.class)
public class OcenaKonobaraEntity {
    private int idGosta;
    private int mbrKonobara;
    private int ocena;

    @Id
    @Column(name = "ID_GOSTA")
    public int getIdGosta() {
        return idGosta;
    }

    public void setIdGosta(int idGosta) {
        this.idGosta = idGosta;
    }

    @Id
    @Column(name = "MBR_KONOBARA")
    public int getMbrKonobara() {
        return mbrKonobara;
    }

    public void setMbrKonobara(int mbrKonobara) {
        this.mbrKonobara = mbrKonobara;
    }

    @Id
    @Column(name = "OCENA")
    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OcenaKonobaraEntity that = (OcenaKonobaraEntity) o;

        if (idGosta != that.idGosta) return false;
        if (mbrKonobara != that.mbrKonobara) return false;
        if (ocena != that.ocena) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGosta;
        result = 31 * result + mbrKonobara;
        result = 31 * result + ocena;
        return result;
    }
}
