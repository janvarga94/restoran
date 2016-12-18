package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "jelo_u_porudzbini", schema = "restorani", catalog = "")
public class JeloUPorudzbiniEntity {
    private String nazivJela;
    private int id;
    private Integer mbrKuvara;

    @Basic
    @Column(name = "NAZIV_JELA")
    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MBR_KUVARA")
    public Integer getMbrKuvara() {
        return mbrKuvara;
    }

    public void setMbrKuvara(Integer mbrKuvara) {
        this.mbrKuvara = mbrKuvara;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeloUPorudzbiniEntity that = (JeloUPorudzbiniEntity) o;

        if (id != that.id) return false;
        if (nazivJela != null ? !nazivJela.equals(that.nazivJela) : that.nazivJela != null) return false;
        if (mbrKuvara != null ? !mbrKuvara.equals(that.mbrKuvara) : that.mbrKuvara != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazivJela != null ? nazivJela.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (mbrKuvara != null ? mbrKuvara.hashCode() : 0);
        return result;
    }
}
