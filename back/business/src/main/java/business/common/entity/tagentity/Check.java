package business.common.entity.tagentity;

import java.io.Serializable;

public class Check implements Serializable {

    private Long workerId;

    private boolean judge;

    private Integer checkTime;

    public Check(){}

    public Check(Long workerId, boolean judge, Integer checkTime) {
        this.workerId = workerId;
        this.judge = judge;
        this.checkTime = checkTime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public Integer getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public String toString() {
        return "Check{" +
                "workerId=" + workerId +
                ", judge=" + judge +
                ", checkTime=" + checkTime +
                '}';
    }
}
