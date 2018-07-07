package business.recommendation.vo;

import business.common.returnmodel.VO;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class FeedbackVO extends VO implements Serializable {

    private Long workerId;
    private Long missionId;
    private Integer event;

    public FeedbackVO(){}

    public FeedbackVO(Long workerId, Long missionId, Integer event) {
        this.workerId = workerId;
        this.missionId = missionId;
        this.event = event;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "FeedbackVO{" +
                "workerId=" + workerId +
                ", missionId=" + missionId +
                ", event=" + event +
                '}';
    }
}
