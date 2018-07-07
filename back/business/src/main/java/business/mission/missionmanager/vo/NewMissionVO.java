package business.mission.missionmanager.vo;

public class NewMissionVO {

    private NewMissionInfoReturnVO[] missions;

    public NewMissionVO(){}

    public NewMissionVO(NewMissionInfoReturnVO[] missions){
        this.missions=missions;
    }

    public NewMissionInfoReturnVO[] getMissions() {
        return missions;
    }

    public void setMissions(NewMissionInfoReturnVO[] missions) {
        this.missions = missions;
    }
}
