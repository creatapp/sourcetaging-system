package business.common.entity.logentity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Transactional
@Entity
@Table(name = "log")
public class LogEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long operatorId;

    private String role;

    private Long missionId;

    private String operation;//操作

    private String time;

    public LogEntity(Long operatorId, String role, Long missionId, String operation) {
        this.operatorId = operatorId;
        this.role=role;
        this.missionId = missionId;
        this.operation = operation;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = sdf.format(d);
    }

    public LogEntity() {
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id=" + id +
                ", operatorId=" + operatorId +
                ", role='" + role + '\'' +
                ", missionId=" + missionId +
                ", operation='" + operation + '\'' +
                ", time=" + time +
                '}';
    }
}
