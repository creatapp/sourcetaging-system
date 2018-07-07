package business.common.entity.workerentity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "donemission")
public class DoneMissionEntity implements Serializable {

    @Id
    private long missionId;

    private String missionKind;

    private Integer rightNum;

    private Integer wrongNum;

    private Integer totalNum;

    private Integer toBeJudgeNum;

    private Integer point;

    private Date initTime;//以时间区分同一任务的不同部分

    //新增，生成时可以直接从doingMission里复制过来，表示已经该任务做过的标注
    private Long[] doneTagId;

    public DoneMissionEntity(long missionId, String missionKind, Integer rightNum, Integer wrongNum,
                             Integer totalNum, Integer toBeJudgeNum, Integer point, Date initTime,
                             Long[] doneTagId) {
        this.missionId = missionId;
        this.missionKind = missionKind;
        this.rightNum = rightNum;
        this.wrongNum = wrongNum;
        this.totalNum = totalNum;
        this.toBeJudgeNum = toBeJudgeNum;
        this.point = point;
        this.initTime = initTime;
        this.doneTagId = doneTagId;
    }

    public DoneMissionEntity(){}

    public long getMissionId() {
        return missionId;
    }

    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }

    public String getMissionKind() {
        return missionKind;
    }

    public void setMissionKind(String missionKind) {
        this.missionKind = missionKind;
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    public Integer getWrongNum() {
        return wrongNum;
    }

    public void setWrongNum(Integer wrongNum) {
        this.wrongNum = wrongNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getToBeJudgeNum() {
        return toBeJudgeNum;
    }

    public void setToBeJudgeNum(Integer toBeJudgeNum) {
        this.toBeJudgeNum = toBeJudgeNum;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Long[] getDoneTagId() {
        return doneTagId;
    }

    public void setDoneTagId(Long[] doneTagId) {
        this.doneTagId = doneTagId;
    }

    @Override
    public String toString() {
        return "DoneMissionEntity{" +
                "missionId='" + missionId + '\'' +
                ", missionKind='" + missionKind + '\'' +
                ", rightNum=" + rightNum +
                ", wrongNum=" + wrongNum +
                ", totalNum=" + totalNum +
                ", toBeJudgeNum=" + toBeJudgeNum +
                ", point=" + point +
                ", initTime=" + initTime +
                ", doneTagId=" + Arrays.toString(doneTagId) +
                '}';
    }
}
