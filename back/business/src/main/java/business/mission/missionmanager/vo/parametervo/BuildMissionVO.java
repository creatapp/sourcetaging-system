package business.mission.missionmanager.vo.parametervo;

import java.util.Arrays;

public class BuildMissionVO {

    private Long requesterId;

    private String title;

    private String description;

    private String type;

    private Integer[] classLabel;

    private String[] pics;

    private Integer perPoints;

    public BuildMissionVO(Long requesterId, String title, String description, String type, Integer[] classLabel, String[] pictures, Integer perPicPoints) {
        this.requesterId = requesterId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.classLabel=classLabel;
        this.pics = pictures;
        this.perPoints = perPicPoints;
    }

    public BuildMissionVO(){}

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
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

    public String getType() {
        return type;
    }

    public Integer[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(Integer[] classLabel) {
        this.classLabel = classLabel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pictures) {
        this.pics = pictures;
    }

    public Integer getPerPoints() {
        return perPoints;
    }

    public void setPerPoints(Integer perPicPoints) {
        this.perPoints = perPicPoints;
    }

    @Override
    public String toString() {
        return "BuildMissionVO{" +
                "requesterId=" + requesterId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", classLabel=" + Arrays.toString(classLabel) +
                ", pics=" + Arrays.toString(pics) +
                ", perPoints=" + perPoints +
                '}';
    }
}
