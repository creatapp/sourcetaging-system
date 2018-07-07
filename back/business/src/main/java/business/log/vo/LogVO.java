package business.log.vo;

import business.common.returnmodel.VO;

import java.util.Date;

public class LogVO extends VO{

    private Long operatorId;

    private String role;

    private Long missionId;

    private String operation;//操作

    private String time;

    public LogVO(Long operatorId, String role, Long missionId, String operation, String time) {
        this.operatorId = operatorId;
        this.role = role;
        this.missionId = missionId;
        this.operation = operation;
        this.time = time;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LogVO{" +
                "operatorId=" + operatorId +
                ", role='" + role + '\'' +
                ", missionId=" + missionId +
                ", operation='" + operation + '\'' +
                ", time=" + time +
                '}';
    }
}
