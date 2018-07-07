package business.common.entity.missionentity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Entity
@Table(name = "hotmission")
public class HotMissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "tableGenerator_hotMission")
    @TableGenerator(name="tableGenerator_hotMission",initialValue=0,allocationSize=1)
    private Long rank;

    private Long missionId;

    private double hotRefer;

    public HotMissionEntity(Long missionId, double hotRefer) {
        this.missionId = missionId;
        this.hotRefer = hotRefer;
    }

    public HotMissionEntity() {
        this.missionId = new Long(-1);
        this.hotRefer = 0.0;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public double getHotRefer() {
        return hotRefer;
    }

    public void setHotRefer(double hotRefer) {
        this.hotRefer = hotRefer;
    }

    @Override
    public String toString() {
        return "HotMissionEntity{" +
                "rank=" + rank +
                ", missionId=" + missionId +
                ", hotRefer=" + hotRefer +
                '}';
    }
}
