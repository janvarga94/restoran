package init.modelFromDB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 2/26/2017.
 */
public class PrijateljstvoEntityPK implements Serializable {
    private String prviEmail;
    private String drugiEmail;

    @Column(name = "PRVI_EMAIL")
    @Id
    public String getPrviEmail() {
        return prviEmail;
    }

    public void setPrviEmail(String prviEmail) {
        this.prviEmail = prviEmail;
    }

    @Column(name = "DRUGI_EMAIL")
    @Id
    public String getDrugiEmail() {
        return drugiEmail;
    }

    public void setDrugiEmail(String drugiEmail) {
        this.drugiEmail = drugiEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrijateljstvoEntityPK that = (PrijateljstvoEntityPK) o;

        if (prviEmail != null ? !prviEmail.equals(that.prviEmail) : that.prviEmail != null) return false;
        if (drugiEmail != null ? !drugiEmail.equals(that.drugiEmail) : that.drugiEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prviEmail != null ? prviEmail.hashCode() : 0;
        result = 31 * result + (drugiEmail != null ? drugiEmail.hashCode() : 0);
        return result;
    }
}
