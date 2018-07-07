package business.common.entity.requesterentity;

import business.common.entity.FatherEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "requester")
public class RequesterEntity extends FatherEntity implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long requesterId;//通过此可以得到对应的其它普通属性

    private Integer releasedMissionsNum;//发布的任务数量
    @Lob()
    private Long[] releasedMissionsList;//missionId合集

    private Integer paidPoints;//支付积分

    private Integer balance;//积分余额

    public RequesterEntity(Integer releasedMissionsNum,
                           Long[] releasedMissionsList, Integer paidPoints,Integer balance) {
        this.releasedMissionsNum = releasedMissionsNum;
        this.releasedMissionsList = releasedMissionsList;
        this.paidPoints = paidPoints;
        this.balance=balance;
    }

    public RequesterEntity(){}

    public long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(long requesterId) {
        this.requesterId = requesterId;
    }

    public Integer getReleasedMissionsNum() {
        return releasedMissionsNum;
    }

    public void setReleasedMissionsNum(Integer releasedMissionsNum) {
        this.releasedMissionsNum = releasedMissionsNum;
    }

    public Long[] getReleasedMissionsList() {
        return releasedMissionsList;
    }

    public void setReleasedMissionsList(Long[] releasedMissionsList) {
        this.releasedMissionsList = releasedMissionsList;
    }

    public Integer getPaidPoints() {
        return paidPoints;
    }

    public void setPaidPoints(Integer paidPoints) {
        this.paidPoints = paidPoints;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "RequesterEntity{" +
                "requesterId='" + requesterId + '\'' +
                ", releasedMissionsNum=" + releasedMissionsNum +
                ", releasedMissionsList=" + Arrays.toString(releasedMissionsList) +
                ", paidPoints=" + paidPoints +
                '}';
    }
}
