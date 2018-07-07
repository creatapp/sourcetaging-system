package business.common.entity.missionentity;

import javax.persistence.*;
import javax.transaction.Transactional;

@Embeddable
public class RecmdMissionEntity {

    private Long missionId;
    private String engineName;//推荐的算法的来源
    private String recommendReason;//推荐原因
    private Double recommendRefer;//推荐指数

    public RecmdMissionEntity(Long missionId, String engineName, String recommendReason, Double recommendRefer) {
        this.missionId = missionId;
        this.engineName = engineName;
        this.recommendReason = recommendReason;
        this.recommendRefer = recommendRefer;
    }

    public RecmdMissionEntity(){
        this.missionId = new Long(-1);
        this.engineName = "";
        this.recommendReason = "";
        this.recommendRefer = 0.0;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    public Double getRecommendRefer() {
        return recommendRefer;
    }

    public void setRecommendRefer(Double recommendRefer) {
        this.recommendRefer = recommendRefer;
    }

    @Override
    public String toString() {
        return "RecmdMissionEntity{" +
                ", missionId=" + missionId +
                ", engineName='" + engineName + '\'' +
                ", recommendReason='" + recommendReason + '\'' +
                ", recommendRefer=" + recommendRefer +
                '}';
    }
}
