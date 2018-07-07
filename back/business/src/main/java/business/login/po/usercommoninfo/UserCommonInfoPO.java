package business.login.po.usercommoninfo;

import java.io.Serializable;

public class UserCommonInfoPO implements Serializable {

    private String nickName;

    private String userType;

    private String password;

    private String email;

    public UserCommonInfoPO(String nickName, String userType, String password, String email) {
        this.nickName = nickName;
        this.userType = userType;
        this.password = password;
        this.email = email;
    }

    public UserCommonInfoPO(){}

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
        return "UserCommonInfoPO{" +
                "nickName='" + nickName + '\'' +
                ", userType='" + userType + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
