package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "pice_u_porudzbini2", schema = "restorani", catalog = "")
public class PiceUPorudzbini2Entity {
    private int idPorudzbinePica;
    private Integer mbrSankera;
    private String nazivPica;
    private Integer idPorudzbine;

    @Id
    @Column(name = "ID_PORUDZBINE_PICA")
    public int getIdPorudzbinePica() {
        return idPorudzbinePica;
    }

    public void setIdPorudzbinePica(int idPorudzbinePica) {
        this.idPorudzbinePica = idPorudzbinePica;
    }

    @Basic
    @Column(name = "MBR_SANKERA")
    public Integer getMbrSankera() {
        return mbrSankera;
    }

    public void setMbrSankera(Integer mbrSankera) {
        this.mbrSankera = mbrSankera;
    }

    @Basic
    @Column(name = "NAZIV_PICA")
    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
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

        PiceUPorudzbini2Entity that = (PiceUPorudzbini2Entity) o;

        if (idPorudzbinePica != that.idPorudzbinePica) return false;
        if (mbrSankera != null ? !mbrSankera.equals(that.mbrSankera) : that.mbrSankera != null) return false;
        if (nazivPica != null ? !nazivPica.equals(that.nazivPica) : that.nazivPica != null) return false;
        if (idPorudzbine != null ? !idPorudzbine.equals(that.idPorudzbine) : that.idPorudzbine != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPorudzbinePica;
        result = 31 * result + (mbrSankera != null ? mbrSankera.hashCode() : 0);
        result = 31 * result + (nazivPica != null ? nazivPica.hashCode() : 0);
        result = 31 * result + (idPorudzbine != null ? idPorudzbine.hashCode() : 0);
        return result;
    }
}
