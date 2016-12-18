package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "jelo_u_porudzbini2", schema = "restorani", catalog = "")
public class JeloUPorudzbini2Entity {
    private int idPorudzbineJela;
    private Integer mbrKuvara;
    private String nazivJela;
    private Integer idPorudzbine;

    @Id
    @Column(name = "ID_PORUDZBINE_JELA")
    public int getIdPorudzbineJela() {
        return idPorudzbineJela;
    }

    public void setIdPorudzbineJela(int idPorudzbineJela) {
        this.idPorudzbineJela = idPorudzbineJela;
    }

    @Basic
    @Column(name = "MBR_KUVARA")
    public Integer getMbrKuvara() {
        return mbrKuvara;
    }

    public void setMbrKuvara(Integer mbrKuvara) {
        this.mbrKuvara = mbrKuvara;
    }

    @Basic
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Basic
    @Column(name = "ID_PORUDZBINE")
    public Integer getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(Integer idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeloUPorudzbini2Entity that = (JeloUPorudzbini2Entity) o;

        if (idPorudzbineJela != that.idPorudzbineJela) return false;
        if (mbrKuvara != null ? !mbrKuvara.equals(that.mbrKuvara) : that.mbrKuvara != null) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (idPorudzbine != null ? !idPorudzbine.equals(that.idPorudzbine) : that.idPorudzbine != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPorudzbineJela;
        result = 31 * result + (mbrKuvara != null ? mbrKuvara.hashCode() : 0);
        result = 31 * result + (nazivJela != null ? nazivJela.hashCode() : 0);
        result = 31 * result + (idPorudzbine != null ? idPorudzbine.hashCode() : 0);
        return result;
    }
}
