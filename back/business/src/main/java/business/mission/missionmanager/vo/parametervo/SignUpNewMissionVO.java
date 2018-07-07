package business.mission.missionmanager.vo.parametervo;

public class SignUpNewMissionVO {

    private Long workerId;

    private Long missionId;

    private String kind;

    public SignUpNewMissionVO(Long workerId, Long missionId, String kind) {
        this.workerId = workerId;
        this.missionId = missionId;
        this.kind = kind;
    }

    private SignUpNewMissionVO(){}

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "SignUpNewMissionVO{" +
                "workerId='" + workerId + '\'' +
                ", missionId='" + missionId + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
