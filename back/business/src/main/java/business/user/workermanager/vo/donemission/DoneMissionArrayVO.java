package business.user.workermanager.vo.donemission;

import java.io.Serializable;
import java.util.Arrays;

public class DoneMissionArrayVO implements Serializable {

    private DoneMissionVO[] missions;

    public DoneMissionArrayVO(DoneMissionVO[] missions) {
        this.missions = missions;
    }

    public DoneMissionVO[] getMissions() {
        return missions;
    }

    public void setMissions(DoneMissionVO[] missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "DoneMissionArrayVO{" +
                "missions=" + Arrays.toString(missions) +
                '}';
    }
}
