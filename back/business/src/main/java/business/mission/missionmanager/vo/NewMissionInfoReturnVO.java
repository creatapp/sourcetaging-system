package business.mission.missionmanager.vo;

import business.common.returnmodel.VO;

import java.io.Serializable;
import java.util.Arrays;

public class NewMissionInfoReturnVO extends VO implements Serializable {
    private Long id;
    private String title="";
    private String description="";
    private String type="";
    private Integer tagPrice=0;
    private Integer[] classLabel;
    private Integer checkPrice=0;
    private boolean tagLeft;

    public NewMissionInfoReturnVO(){

    }

    public NewMissionInfoReturnVO(Long id, String title, String description, String type, Integer tagPrice,
                                  Integer checkPrice,Integer[] classLabel,boolean tagLeft) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.tagPrice = tagPrice;
        this.checkPrice = checkPrice;
        this.classLabel=classLabel;
        this.tagLeft=tagLeft;
    }

    @Override
    public String toString() {
        return "NewMissionInfoReturnVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", tagPrice=" + tagPrice +
                ", classLabel=" + Arrays.toString(classLabel) +
                ", checkPrice=" + checkPrice +
                ", tagLeft=" + tagLeft +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public boolean isTagLeft() {
        return tagLeft;
    }

    public void setTagLeft(boolean tagLeft) {
        this.tagLeft = tagLeft;
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

    public Integer getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(Integer checkPrice) {
        this.checkPrice = checkPrice;
    }

    public Integer[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(Integer[] classLabel) {
        this.classLabel = classLabel;
    }
}
