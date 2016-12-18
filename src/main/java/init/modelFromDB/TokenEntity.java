package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
@Entity
@Table(name = "token", schema = "restorani", catalog = "")
public class TokenEntity {
    private String tokenString;
    private Integer idGosta;

    @Id
    @Column(name = "TOKEN_STRING")
    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    @Basic
    @Column(name = "ID_GOSTA")
    public Integer getIdGosta() {
        return idGosta;
    }

    public void setIdGosta(Integer idGosta) {
        this.idGosta = idGosta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenEntity that = (TokenEntity) o;

        if (tokenString != null ? !tokenString.equals(that.tokenString) : that.tokenString != null) return false;
        if (idGosta != null ? !idGosta.equals(that.idGosta) : that.idGosta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tokenString != null ? tokenString.hashCode() : 0;
        result = 31 * result + (idGosta != null ? idGosta.hashCode() : 0);
        return result;
    }
}
