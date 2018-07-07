package business.common.entity.userentity;

import business.common.entity.FatherEntity;
import business.login.po.usercommoninfo.UserCommonInfoPO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class UserEntity extends FatherEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=100000000,allocationSize=1)
    private long id;

    private String type;

    private String nickName;

    private String password;

    private String email;

    private boolean available;//如果false表示该账号已经删除

    public UserEntity(String type, String nickName, String password, String email) {
        this.type = type;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.available = true;
    }

    public UserEntity(){}

    public UserEntity(UserCommonInfoPO userCommonInfoPO){
        this.email = userCommonInfoPO.getEmail();
        this.nickName = userCommonInfoPO.getNickName();
        this.password = userCommonInfoPO.getPassword();
        this.type = userCommonInfoPO.getUserType();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", available=" + available +
                '}';
    }
}
