package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/21/2016.
 */
@Entity
@Table(name = "zahtev_za_prijateljstvo", schema = "restorani", catalog = "")
@IdClass(ZahtevZaPrijateljstvoEntityPK.class)
public class ZahtevZaPrijateljstvoEntity {
    private String prviEmail;
    private String drugiEmail;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZahtevZaPrijateljstvoEntity that = (ZahtevZaPrijateljstvoEntity) o;

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
