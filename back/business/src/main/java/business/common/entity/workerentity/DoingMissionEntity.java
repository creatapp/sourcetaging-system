package business.common.entity.workerentity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/*
* 本类用作正在进行中的任务，由WorkerInfoDomain管理
* */

@Entity
@Table(name = "doingmission")
public class DoingMissionEntity implements Serializable {

    @Id
    private long missionId;

    private String missionKind;//check、tag

    private Integer doneNum;

    private Integer totalNum;

    private Date initTime;//如果加入任务后10小时未完成，则删除任务，释放资源

    private Date lastTime;//如果进行任务过程中20min未完成，则该任务将自动移除

    //新增，用于标识工人现在参与的标注情况，在工人请求标或者检查的时候就应该分配好
    //提供一个index指针，这个用于提供下一张图片
    private Long[] doingTagId;
    private Integer tag_index;


    public DoingMissionEntity(long missionId, String missionKind, Integer doneNum, Integer totalNum,
                              Date initTime, Date lastTime, Long[] doingTagId, Integer tag_index) {
        this.missionId = missionId;
        this.missionKind = missionKind;
        this.doneNum = doneNum;
        this.totalNum = totalNum;
        this.initTime = initTime;
        this.lastTime = lastTime;
        this.doingTagId = doingTagId;
        this.tag_index = tag_index;
    }

    public DoingMissionEntity(){}

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

    public Integer getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(Integer doneNum) {
        this.doneNum = doneNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Long[] getDoingTagId() {
        return doingTagId;
    }

    public void setDoingTagId(Long[] doingTagId) {
        this.doingTagId = doingTagId;
    }

    public Integer getTag_index() {
        return tag_index;
    }

    public void setTag_index(Integer tag_index) {
        this.tag_index = tag_index;
    }


    @Override
    public String toString() {
        return "DoingMissionEntity{" +
                "missionId='" + missionId + '\'' +
                ", missionKind='" + missionKind + '\'' +
                ", doneNum=" + doneNum +
                ", totalNum=" + totalNum +
                ", initTime=" + initTime +
                ", lastTime=" + lastTime +
                ", doingTagId=" + Arrays.toString(doingTagId) +
                ", tag_index=" + tag_index +
                '}';
    }
}
