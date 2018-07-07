package business.common.entity.tagentity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "tag")
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;

    private Long missionId;

    private Long pictureId;

    private Long workerId;

    @Lob()
    private Tag tag;//标注情况
    @Lob()
    private Check[] checks;

    private Integer checkLeftTimes;

    private Long pictureTime;

    private boolean hasFinished;

    private boolean available;//如果false就会被删除


    public TagEntity(Long missionId, Long pictureId, Long workerId,
                     Tag tag, Check[] checks,Long pictureTime,
                     Integer checkLeftTimes, boolean hasFinished, boolean available) {
        this.missionId = missionId;
        this.pictureId = pictureId;
        this.pictureTime=pictureTime;
        this.workerId = workerId;
        this.tag = tag;
        this.checks = checks;
        this.checkLeftTimes = checkLeftTimes;
        this.hasFinished = hasFinished;
        this.available = available;
    }

    public TagEntity(){}

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getPictureTime() {
        return pictureTime;
    }

    public void setPictureTime(Long pictureTime) {
        this.pictureTime = pictureTime;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Check[] getChecks() {
        return checks;
    }

    public void setChecks(Check[] checks) {
        this.checks = checks;
    }

    public Integer getCheckLeftTimes() {
        return checkLeftTimes;
    }

    public void setCheckLeftTimes(Integer checkLeftTimes) {
        this.checkLeftTimes = checkLeftTimes;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "tagId=" + tagId +
                ", missionId=" + missionId +
                ", pictureId=" + pictureId +
                ", workerId=" + workerId +
                ", tag=" + tag +
                ", checks=" + Arrays.toString(checks) +
                ", checkLeftTimes=" + checkLeftTimes +
                ", pictureTime=" + pictureTime +
                ", hasFinished=" + hasFinished +
                ", available=" + available +
                '}';
    }
}
