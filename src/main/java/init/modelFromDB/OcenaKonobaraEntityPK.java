package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
public class OcenaKonobaraEntityPK implements Serializable {
    private int idGosta;
    private int mbrKonobara;
    private int ocena;

    @Column(name = "ID_GOSTA")
    @Id
    public int getIdGosta() {
        return idGosta;
    }

    public void setIdGosta(int idGosta) {
        this.idGosta = idGosta;
    }

    @Column(name = "MBR_KONOBARA")
    @Id
    public int getMbrKonobara() {
        return mbrKonobara;
    }

    public void setMbrKonobara(int mbrKonobara) {
        this.mbrKonobara = mbrKonobara;
    }

    @Column(name = "OCENA")
    @Id
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

        OcenaKonobaraEntityPK that = (OcenaKonobaraEntityPK) o;

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
