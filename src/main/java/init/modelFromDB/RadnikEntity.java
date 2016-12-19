package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
@Entity
@Table(name = "radnik", schema = "restorani", catalog = "")
public class RadnikEntity {
    private String prezime;
    private Date rodjen;
    private Integer konfekcijskiBroj;
    private Integer velicinaObuce;
    private int mbr;
    private Integer idRestorana;
    private String ime;

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

    @Basic
    @Column(name = "IME")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RadnikEntity that = (RadnikEntity) o;

        if (mbr != that.mbr) return false;
        if (prezime != null ? !prezime.equals(that.prezime) : that.prezime != null) return false;
        if (rodjen != null ? !rodjen.equals(that.rodjen) : that.rodjen != null) return false;
        if (konfekcijskiBroj != null ? !konfekcijskiBroj.equals(that.konfekcijskiBroj) : that.konfekcijskiBroj != null)
            return false;
        if (velicinaObuce != null ? !velicinaObuce.equals(that.velicinaObuce) : that.velicinaObuce != null)
            return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (ime != null ? !ime.equals(that.ime) : that.ime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prezime != null ? prezime.hashCode() : 0;
        result = 31 * result + (rodjen != null ? rodjen.hashCode() : 0);
        result = 31 * result + (konfekcijskiBroj != null ? konfekcijskiBroj.hashCode() : 0);
        result = 31 * result + (velicinaObuce != null ? velicinaObuce.hashCode() : 0);
        result = 31 * result + mbr;
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (ime != null ? ime.hashCode() : 0);
        return result;
    }
}
