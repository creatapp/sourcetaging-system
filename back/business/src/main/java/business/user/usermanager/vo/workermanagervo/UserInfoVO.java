package business.user.usermanager.vo.workermanagervo;

import business.common.returnmodel.VO;

import java.io.Serializable;

public class UserInfoVO extends VO implements Serializable {

    private Long id;

    private String nickname;

    private String email;

    private Integer points;

    public UserInfoVO(Long id, String nickname, String email, Integer points) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.points = points;
    }

    public UserInfoVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
