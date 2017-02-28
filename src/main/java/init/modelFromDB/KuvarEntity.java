package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/28/2017.
 */
@Entity
@Table(name = "kuvar", schema = "restorani", catalog = "")
public class KuvarEntity {
    private Integer idTipaJela;
    private String kuvarEmail;

    @Basic
    @Column(name = "ID_TIPA_JELA")
    public Integer getIdTipaJela() {
        return idTipaJela;
    }

    public void setIdTipaJela(Integer idTipaJela) {
        this.idTipaJela = idTipaJela;
    }

    @Id
    @Column(name = "KUVAR_EMAIL")
    public String getKuvarEmail() {
        return kuvarEmail;
    }

    public void setKuvarEmail(String kuvarEmail) {
        this.kuvarEmail = kuvarEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KuvarEntity that = (KuvarEntity) o;

        if (idTipaJela != null ? !idTipaJela.equals(that.idTipaJela) : that.idTipaJela != null) return false;
        if (kuvarEmail != null ? !kuvarEmail.equals(that.kuvarEmail) : that.kuvarEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipaJela != null ? idTipaJela.hashCode() : 0;
        result = 31 * result + (kuvarEmail != null ? kuvarEmail.hashCode() : 0);
        return result;
    }
}
