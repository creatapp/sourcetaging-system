package business.user.usermanager.vo.workermanagervo;

import business.common.returnmodel.VO;

import java.io.Serializable;

//这是返回给前端的工人信息的类，信息将从workerinfodomain中抽取
public class WorkerInfoVO extends VO implements Serializable {

    //common
    private Long id;

    private String nickname;

    private String email;

    private Integer points;

    //system
    private Integer rank;

    private Integer doingMissions;

    private Integer doneMissions;

    private Integer rightAns;

    private Integer wrongAns;

    private Integer toBeJudgeAns;

    private Integer totalAns;

    private Double ansAccuracy;


    public WorkerInfoVO(Long id, String nickname, String email, Integer points, Integer rank,
                        Integer doingMissions, Integer doneMissions, Integer rightAns,
                        Integer wrongAns, Integer toBeJudgeAns, Integer totalAns, Double ansAccuracy) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.points = points;
        this.rank = rank;
        this.doingMissions = doingMissions;
        this.doneMissions = doneMissions;
        this.rightAns = rightAns;
        this.wrongAns = wrongAns;
        this.toBeJudgeAns = toBeJudgeAns;
        this.totalAns = totalAns;
        this.ansAccuracy = ansAccuracy;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getDoingMissions() {
        return doingMissions;
    }

    public void setDoingMissions(Integer doingMissions) {
        this.doingMissions = doingMissions;
    }

    public Integer getDoneMissions() {
        return doneMissions;
    }

    public void setDoneMissions(Integer doneMissions) {
        this.doneMissions = doneMissions;
    }

    public Integer getRightAns() {
        return rightAns;
    }

    public void setRightAns(Integer rightAns) {
        this.rightAns = rightAns;
    }

    public Integer getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(Integer wrongAns) {
        this.wrongAns = wrongAns;
    }

    public Integer getToBeJudgeAns() {
        return toBeJudgeAns;
    }

    public void setToBeJudgeAns(Integer toBeJudgeAns) {
        this.toBeJudgeAns = toBeJudgeAns;
    }

    public Integer getTotalAns() {
        return totalAns;
    }

    public void setTotalAns(Integer totalAns) {
        this.totalAns = totalAns;
    }

    public Double getAnsAccuracy() {
        return ansAccuracy;
    }

    public void setAnsAccuracy(Double ansAccuracy) {
        this.ansAccuracy = ansAccuracy;
    }


    @Override
    public String toString() {
        return "WorkerInfoVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", rank=" + rank +
                ", doingMissions=" + doingMissions +
                ", doneMissions=" + doneMissions +
                ", rightAns=" + rightAns +
                ", wrongAns=" + wrongAns +
                ", toBeJudgeAns=" + toBeJudgeAns +
                ", totalAns=" + totalAns +
                ", ansAccuracy=" + ansAccuracy +
                '}';
    }
}
