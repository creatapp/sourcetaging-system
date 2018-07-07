package business.user.usermanager.vo.workermanagervo;

import java.io.Serializable;
import java.util.Arrays;

public class WorkerInfoArrayVO implements Serializable {

    private WorkerInfoVO[] workers;

    public WorkerInfoArrayVO(WorkerInfoVO[] workers) {
        this.workers = workers;
    }

    public WorkerInfoVO[] getWorkers() {
        return workers;
    }

    public void setWorkers(WorkerInfoVO[] workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "WorkerInfoArrayVO{" +
                "workers=" + Arrays.toString(workers) +
                '}';
    }
}
