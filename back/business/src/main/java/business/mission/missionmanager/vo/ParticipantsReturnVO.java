package business.mission.missionmanager.vo;


import business.common.returnmodel.VO;

import java.io.Serializable;

public class ParticipantsReturnVO extends VO implements Serializable {

    private Long id;
    private String nickname="";
    private String email="";
    private Integer points=0;

    public ParticipantsReturnVO(){

    }

    public ParticipantsReturnVO(Long id, String nickname, String email, Integer points){
        this.id=id;
        this.nickname=nickname;
        this.email=email;
        this.points=points;
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

    @Override
    public String toString() {
        return "ParticipantsReturnVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                '}';
    }
}
