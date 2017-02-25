package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "radnik", schema = "restorani", catalog = "")
public class RadnikEntity {
    private Integer konfekcijskiBroj;
    private Integer velicinaObuce;
    private Integer idRestorana;
    private String radnikEmail;

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

    @Basic
    @Column(name = "ID_RESTORANA")
    public Integer getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Integer idRestorana) {
        this.idRestorana = idRestorana;
    }

    @Id
    @Column(name = "RADNIK_EMAIL")
    public String getRadnikEmail() {
        return radnikEmail;
    }

    public void setRadnikEmail(String radnikEmail) {
        this.radnikEmail = radnikEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RadnikEntity that = (RadnikEntity) o;

        if (konfekcijskiBroj != null ? !konfekcijskiBroj.equals(that.konfekcijskiBroj) : that.konfekcijskiBroj != null)
            return false;
        if (velicinaObuce != null ? !velicinaObuce.equals(that.velicinaObuce) : that.velicinaObuce != null)
            return false;
        if (idRestorana != null ? !idRestorana.equals(that.idRestorana) : that.idRestorana != null) return false;
        if (radnikEmail != null ? !radnikEmail.equals(that.radnikEmail) : that.radnikEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = konfekcijskiBroj != null ? konfekcijskiBroj.hashCode() : 0;
        result = 31 * result + (velicinaObuce != null ? velicinaObuce.hashCode() : 0);
        result = 31 * result + (idRestorana != null ? idRestorana.hashCode() : 0);
        result = 31 * result + (radnikEmail != null ? radnikEmail.hashCode() : 0);
        return result;
    }
}
