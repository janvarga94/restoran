package init.modelFromDB;

import javax.persistence.*;

/**
 * Created by Svetozar Stojkovic on 2/25/2017.
 */
@Entity
@Table(name = "token", schema = "restorani", catalog = "")
public class TokenEntity {
    private String tokenString;
    private String email;

    @Id
    @Column(name = "TOKEN_STRING")
    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenEntity that = (TokenEntity) o;

        if (tokenString != null ? !tokenString.equals(that.tokenString) : that.tokenString != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tokenString != null ? tokenString.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
