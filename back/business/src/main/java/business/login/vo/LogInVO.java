package business.login.vo;

import java.io.Serializable;

public class LogInVO implements Serializable {
    private String type;

    private Long id;

    private String password;

    public LogInVO(String type, Long id, String password) {
        this.type = type;
        this.id = id;
        this.password = password;
    }

    public LogInVO(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LogInVO{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
