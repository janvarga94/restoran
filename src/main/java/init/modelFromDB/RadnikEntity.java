package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "radnik", schema = "restorani", catalog = "")
public class RadnikEntity {
    private String column1;
    private String prezime;
    private Date rodjen;
    private Integer konfekcijskiBroj;
    private Integer velicinaObuce;
    private int mbr;
    private Integer idRestorana;

    @Basic
    @Column(name = "COLUMN_1")
    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    @Basic
    @Column(name = "PREZIME")
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Basic
    @Column(name = "RODJEN")
    public Date getRodjen() {
        return rodjen;
    }

    public void setRodjen(Date rodjen) {
        this.rodjen = rodjen;
    }

    @Basic
    @Column(name = "KONFEKCIJSKI_BROJ")
    public Integer getKonfekcijskiBroj() {
        return konfekcijskiBroj;
    }

    public void setKonfekcijskiBroj(Integer konfekcijskiBroj) {
        this.konfekcijskiBroj = konfekcijskiBroj;
    }

    @Basic
    @Column(name = "VELICINA_OBUCE")
    public Integer getVelicinaObuce() {
        return velicinaObuce;
    }

    public void setVelicinaObuce(Integer velicinaObuce) {
        this.velicinaObuce = velicinaObuce;
    }

    @Id
    @Column(name = "MBR")
    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RadnikEntity that = (RadnikEntity) o;

        if (mbr != that.mbr) return false;
        if (column1 != null ? !column1.equals(that.column1) : that.column1 != null) return false;
        if (prezime != null ? !prezime.equals(that.prezime) : that.prezime != null) return false;
        if (rodjen != null ? !rodjen.equals(that.rodjen) : that.rodjen != null) return false;
        if (konfekcijskiBroj != null ? !konfekcijskiBroj.equals(that.konfekcijskiBroj) : that.konfekcijskiBroj != null)
            return false;
        if (velicinaObuce != null ? !velicinaObuce.equals(that.velicinaObuce) : that.velicinaObuce != null)
            return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = column1 != null ? column1.hashCode() : 0;
        result = 31 * result + (prezime != null ? prezime.hashCode() : 0);
        result = 31 * result + (rodjen != null ? rodjen.hashCode() : 0);
        result = 31 * result + (konfekcijskiBroj != null ? konfekcijskiBroj.hashCode() : 0);
        result = 31 * result + (velicinaObuce != null ? velicinaObuce.hashCode() : 0);
        result = 31 * result + mbr;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        return result;
    }
}
