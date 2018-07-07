package business.user.usermanager.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/*
* 这个是用户的最基本的信息PO
* */
@Component
public class UserInfoPO implements Serializable {

    private String userType = "";

    private String userId = "";

    private String nickName = "";

    private String mail = "";

    private String password = "";

    public UserInfoPO(){}

    public UserInfoPO(String userType, String userId, String password, String nickName, String mail) {
        this.userType = userType;
        this.userId = userId;
        this.nickName = nickName;
        this.mail = mail;
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
