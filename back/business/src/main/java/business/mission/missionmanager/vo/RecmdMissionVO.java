package business.mission.missionmanager.vo;

import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.returnmodel.VO;

import java.io.Serializable;
import java.util.Arrays;

public class RecmdMissionVO extends VO implements Serializable{
    private Long id;
    private Double recommendRefer;//推荐指数
    private String reason;
    private String title;
    private String description;
    private String type;
    private Integer tagPrice;
    private Integer[] classLabel;
    private Integer checkPrice;
    private boolean tagLeft;

    public RecmdMissionVO(RecmdMissionEntity recmdMissionEntity, MissionInfoReturnVO missionInfoReturnVO) {
        this.id = recmdMissionEntity.getMissionId();
        this.recommendRefer = recmdMissionEntity.getRecommendRefer();
        this.title=missionInfoReturnVO.getTitle();
        this.description=missionInfoReturnVO.getDescription();
        this.type=missionInfoReturnVO.getType();
        this.tagPrice=missionInfoReturnVO.getTagPrice();
        this.classLabel=missionInfoReturnVO.getClassLabel();
        this.checkPrice=missionInfoReturnVO.getCheckPrice();
        this.tagLeft=missionInfoReturnVO.isTagLeft();
        this.reason=recmdMissionEntity.getRecommendReason();
    }

    public RecmdMissionVO(){}

    public Double getRecommendRefer() {
        return recommendRefer;
    }

    public void setRecommendRefer(Double recommendRefer) {
        this.recommendRefer = recommendRefer;
    }

    public String getTitle() {
        return title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(Integer tagPrice) {
        this.tagPrice = tagPrice;
    }

    public Integer[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(Integer[] classLabel) {
        this.classLabel = classLabel;
    }

    public Integer getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(Integer checkPrice) {
        this.checkPrice = checkPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTagLeft() {
        return tagLeft;
    }

    public void setTagLeft(boolean tagLeft) {
        this.tagLeft = tagLeft;
    }

    @Override
    public String toString() {
        return "RecmdMissionVO{" +
                "id=" + id +
                ", recommendRefer=" + recommendRefer +
                ", reason='" + reason + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", tagPrice=" + tagPrice +
                ", classLabel=" + Arrays.toString(classLabel) +
                ", checkPrice=" + checkPrice +
                ", tagLeft=" + tagLeft +
                '}';
    }
}
