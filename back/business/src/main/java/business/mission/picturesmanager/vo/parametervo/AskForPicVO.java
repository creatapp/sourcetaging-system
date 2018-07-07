package business.mission.picturesmanager.vo.parametervo;

public class AskForPicVO {

    private Long missionId;

    private String kind;

    private Long picId;

    private Long workerId;

    public AskForPicVO(Long missionId, String kind, Long picId, Long workerId) {
        this.missionId = missionId;
        this.kind = kind;
        this.picId = picId;
        this.workerId = workerId;
    }

    public AskForPicVO(){}

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    @Override
    public String toString() {
        return "AskForPicVO{" +
                "missionId='" + missionId + '\'' +
                ", kind='" + kind + '\'' +
                ", picId='" + picId + '\'' +
                ", workerId='" + workerId + '\'' +
                '}';
    }
}
