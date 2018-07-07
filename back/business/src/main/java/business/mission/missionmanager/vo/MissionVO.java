package business.mission.missionmanager.vo;

import java.util.Arrays;

public class MissionVO {

    private MissionInfoReturnVO[] missions;

    public MissionVO(){}

    public MissionVO(MissionInfoReturnVO[] missions){
        this.missions=missions;
    }

    @Override
    public String toString() {
        return "MissionVO{" +
                "missions=" + Arrays.toString(missions) +
                '}';
    }

    public MissionInfoReturnVO[] getMissions() {
        return missions;
    }

    public void setMissions(MissionInfoReturnVO[] missions) {
        this.missions = missions;
    }
}
