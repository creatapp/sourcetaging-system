package business.login.vo;

import java.io.Serializable;

public class RegisterVO implements Serializable {

    private String type;

    private String nickname;

    private String password;

    private String email;

    public RegisterVO(String type, String nickname, String password, String email) {
        this.type = type;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public RegisterVO(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterVO{" +
                "type='" + type + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
