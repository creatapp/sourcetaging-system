package business.login.auth.domain;

import java.io.Serializable;

public class UserToken implements Serializable {

    private Long id;

    private String type;

    private String password;

    public UserToken(Long id, String type, String password) {
        this.id = id;
        this.type = type;
        this.password = password;
    }

    public UserToken(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
