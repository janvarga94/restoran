package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "raspored_rada", schema = "restorani", catalog = "")
@IdClass(RasporedRadaEntityPK.class)
public class RasporedRadaEntity {
    private int idSmene;
    private int mbr;
    private Date datumPocetka;
    private Date datumKraja;

    @Id
    @Column(name = "ID_SMENE")
    public int getIdSmene() {
        return idSmene;
    }

    public void setIdSmene(int idSmene) {
        this.idSmene = idSmene;
    }

    @Id
    @Column(name = "MBR")
    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    @Id
    @Column(name = "DATUM_POCETKA")
    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    @Basic
    @Column(name = "DATUM_KRAJA")
    public Date getDatumKraja() {
        return datumKraja;
    }

    public void setDatumKraja(Date datumKraja) {
        this.datumKraja = datumKraja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RasporedRadaEntity that = (RasporedRadaEntity) o;

        if (idSmene != that.idSmene) return false;
        if (mbr != that.mbr) return false;
        if (datumPocetka != null ? !datumPocetka.equals(that.datumPocetka) : that.datumPocetka != null) return false;
        if (datumKraja != null ? !datumKraja.equals(that.datumKraja) : that.datumKraja != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSmene;
        result = 31 * result + mbr;
        result = 31 * result + (datumPocetka != null ? datumPocetka.hashCode() : 0);
        result = 31 * result + (datumKraja != null ? datumKraja.hashCode() : 0);
        return result;
    }
}
