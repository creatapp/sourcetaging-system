package business.common.entity.workerentity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "workerinfo")
public class WorkerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer totalTagNum;

    private Integer rightTagNum;

    private double checkAccuracy;

    private double tagAccuracy;

    private Integer totalCheckNum;

    private Integer rightCheckNum;

    private Integer traitRefer;

    private Integer interestRefer;

    private Date lastTraitTime;

    private Date lastInterestTime;

    public WorkerInfoEntity(Integer totalTagNum, Integer rightTagNum, double checkAccuracy, double tagAccuracy, Integer totalCheckNum
            , Integer rightCheckNum, Integer traitRefer, Integer interestRefer, Date lastTraitTime, Date lastInterestTime) {
        this.totalTagNum = totalTagNum;
        this.rightTagNum = rightTagNum;
        this.checkAccuracy = checkAccuracy;
        this.tagAccuracy = tagAccuracy;
        this.totalCheckNum = totalCheckNum;
        this.rightCheckNum = rightCheckNum;
        this.traitRefer = traitRefer;
        this.interestRefer = interestRefer;
        this.lastTraitTime = lastTraitTime;
        this.lastInterestTime = lastInterestTime;
    }

    public WorkerInfoEntity(){
        this.totalTagNum = 0;
        this.rightTagNum = 0;
        this.checkAccuracy = 0.0;
        this.tagAccuracy = 0.0;
        this.totalCheckNum = 0;
        this.rightCheckNum = 0;
        this.traitRefer = 1;
        this.interestRefer = 1;
        this.lastTraitTime = new Date();
        this.lastInterestTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalTagNum() {
        return totalTagNum;
    }

    public void setTotalTagNum(Integer totalTagNum) {
        this.totalTagNum = totalTagNum;
    }

    public Integer getRightTagNum() {
        return rightTagNum;
    }

    public void setRightTagNum(Integer rightTagNum) {
        this.rightTagNum = rightTagNum;
    }

    public double getCheckAccuracy() {
        return checkAccuracy;
    }

    public void setCheckAccuracy(double checkAccuracy) {
        this.checkAccuracy = checkAccuracy;
    }

    public Integer getTotalCheckNum() {
        return totalCheckNum;
    }

    public void setTotalCheckNum(Integer totalCheckNum) {
        this.totalCheckNum = totalCheckNum;
    }

    public Integer getRightCheckNum() {
        return rightCheckNum;
    }

    public void setRightCheckNum(Integer rightCheckNum) {
        this.rightCheckNum = rightCheckNum;
    }

    public Integer getTraitRefer() {
        return traitRefer;
    }

    public void setTraitRefer(Integer traitRefer) {
        this.traitRefer = traitRefer;
    }

    public Integer getInterestRefer() {
        return interestRefer;
    }

    public void setInterestRefer(Integer interestRefer) {
        this.interestRefer = interestRefer;
    }

    public Date getLastTraitTime() {
        return lastTraitTime;
    }

    public void setLastTraitTime(Date lastTraitTime) {
        this.lastTraitTime = lastTraitTime;
    }

    public Date getLastInterestTime() {
        return lastInterestTime;
    }

    public void setLastInterestTime(Date lastInterestTime) {
        this.lastInterestTime = lastInterestTime;
    }

    public double getTagAccuracy() {
        return tagAccuracy;
    }

    public void setTagAccuracy(double tagAccuracy) {
        this.tagAccuracy = tagAccuracy;
    }

    @Override
    public String toString() {
        return "WorkerInfoEntity{" +
                "id=" + id +
                ", totalTagNum=" + totalTagNum +
                ", rightTagNum=" + rightTagNum +
                ", checkAccuracy=" + checkAccuracy +
                ", totalCheckNum=" + totalCheckNum +
                ", rightCheckNum=" + rightCheckNum +
                ", traitRefer=" + traitRefer +
                ", interestRefer=" + interestRefer +
                ", lastTraitTime=" + lastTraitTime +
                ", lastInterestTime=" + lastInterestTime +
                '}';
    }
}
