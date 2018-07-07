package business.user.workermanager.vo.doingmission;

import java.io.Serializable;
import java.util.Arrays;

public class DoingMissionArrayVO implements Serializable {

    private DoingMissionVO[] missions;

    public DoingMissionArrayVO(DoingMissionVO[] missions) {
        this.missions = missions;
    }

    public DoingMissionVO[] getMissions() {
        return missions;
    }

    public void setMissions(DoingMissionVO[] missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "DoingMissionArrayVO{" +
                "missions=" + Arrays.toString(missions) +
                '}';
    }
}
