package business.common.entity.workerentity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Embeddable
public class SimWorkerEntity implements Serializable {

    private Long workerId;

    private Double simRefer;

    public SimWorkerEntity(Long workerId, Double simRefer) {
        this.workerId = workerId;
        this.simRefer = simRefer;
    }

    public SimWorkerEntity() {
        this.workerId = new Long(1);
        this.simRefer = 0.0;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Double getSimRefer() {
        return simRefer;
    }

    public void setSimRefer(Double simRefer) {
        this.simRefer = simRefer;
    }

    @Override
    public String toString() {
        return "SimWorkerEntity{" +
                "workerId=" + workerId +
                ", simRefer=" + simRefer +
                '}';
    }
}
