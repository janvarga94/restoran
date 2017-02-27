package init.modelFromDB;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 2/27/2017.
 */
@Entity
@Table(name = "prijateljstvo", schema = "restorani", catalog = "")
@IdClass(PrijateljstvoEntityPK.class)
public class PrijateljstvoEntity {
    private String prviEmail;
    private String drugiEmail;
    private Date prihvaceno;

    @Id
    @Column(name = "PRVI_EMAIL")
    public String getPrviEmail() {
        return prviEmail;
    }

    public void setPrviEmail(String prviEmail) {
        this.prviEmail = prviEmail;
    }

    @Id
    @Column(name = "DRUGI_EMAIL")
    public String getDrugiEmail() {
        return drugiEmail;
    }

    public void setDrugiEmail(String drugiEmail) {
        this.drugiEmail = drugiEmail;
    }

    @Basic
    @Column(name = "PRIHVACENO")
    public Date getPrihvaceno() {
        return prihvaceno;
    }

    public void setPrihvaceno(Date prihvaceno) {
        this.prihvaceno = prihvaceno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrijateljstvoEntity that = (PrijateljstvoEntity) o;

        if (prviEmail != null ? !prviEmail.equals(that.prviEmail) : that.prviEmail != null) return false;
        if (drugiEmail != null ? !drugiEmail.equals(that.drugiEmail) : that.drugiEmail != null) return false;
        if (prihvaceno != null ? !prihvaceno.equals(that.prihvaceno) : that.prihvaceno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prviEmail != null ? prviEmail.hashCode() : 0;
        result = 31 * result + (drugiEmail != null ? drugiEmail.hashCode() : 0);
        result = 31 * result + (prihvaceno != null ? prihvaceno.hashCode() : 0);
        return result;
    }
}
