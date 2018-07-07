package business.login.vo;

import java.io.Serializable;

public class ChangePasswordVO implements Serializable {


    private String password;

    private String newPassword;

    public ChangePasswordVO( String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public ChangePasswordVO(){}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordVO{" +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
