package business.user.usermanager.vo.requestermanagervo;

import business.common.returnmodel.VO;

import java.io.Serializable;

public class RequesterInfoVO extends VO implements Serializable {

    //common
    private Long id;

    private String nickname;

    private String email;

    //system
    private Integer releasedMissions;

    private Integer paidPoints;

    public RequesterInfoVO(Long id, String nickname, String email, Integer releasedMissions,
                           Integer paidPoints) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.releasedMissions = releasedMissions;
        this.paidPoints = paidPoints;
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

    public Integer getReleasedMissions() {
        return releasedMissions;
    }

    public void setReleasedMissions(Integer releasedMissions) {
        this.releasedMissions = releasedMissions;
    }

    public Integer getPaidPoints() {
        return paidPoints;
    }

    public void setPaidPoints(Integer paidPoints) {
        this.paidPoints = paidPoints;
    }

    @Override
    public String toString() {
        return "RequesterInfoVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", releasedMissions=" + releasedMissions +
                ", paidPoints=" + paidPoints +
                '}';
    }
}
