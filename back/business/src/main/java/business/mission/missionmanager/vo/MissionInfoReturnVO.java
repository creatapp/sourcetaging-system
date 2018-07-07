package business.mission.missionmanager.vo;


import business.common.entity.missionentity.MissionEntity;
import business.common.returnmodel.VO;
import business.mission.helper.Mission_HotDbHelper;

import java.io.Serializable;
import java.util.Arrays;

public class MissionInfoReturnVO extends VO implements Serializable {

    private Long id;
    private String title="";
    private String description="";
    private String type="";
    private Integer tagPrice=0;
    private Integer checkPrice=0;
    private Integer done=0;
    private Integer total=0;
    private Integer totalPoints=0;
    private String publishDate;
    private String finishDate;
    private Integer[] classLabel;
    private Long requesterName;
    private Integer participantsNum;
    private String[] samplePics;
    private boolean tagLeft;



    public MissionInfoReturnVO(){}

    public MissionInfoReturnVO(MissionEntity missionEntity,String[] samplePics) {
        this.id = missionEntity.getMissionId();
        this.title = missionEntity.getTitle();
        this.description = missionEntity.getDescription();
        this.type = missionEntity.getType();
        this.tagPrice = missionEntity.getTagPrice();
        this.checkPrice = missionEntity.getCheckPrice();
        this.done = missionEntity.getPictureFinishedNum();
        this.total = missionEntity.getPictureTotalNum();
        this.totalPoints = missionEntity.getTotalPoints();
        this.publishDate = missionEntity.getStartDate();
        this.finishDate = missionEntity.getEndDate();
        this.classLabel = missionEntity.getLabel().toArray(new Integer[missionEntity.getLabel().size()]);
        this.requesterName = missionEntity.getRequesterId();
        this.participantsNum=missionEntity.getParticipants().length;
        this.samplePics=samplePics;
        if(missionEntity.getPlanToTagQueue().isEmpty()){
            this.tagLeft=false;
        }else{
            this.tagLeft=true;
        }
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTagPrice(Integer tagPrice) {
        this.tagPrice = tagPrice;
    }

    public void setCheckPrice(Integer checkPrice) {
        this.checkPrice = checkPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String[] getSamplePics() {
        return samplePics;
    }

    public void setSamplePics(String[] samplePics) {
        this.samplePics = samplePics;
    }

    public boolean isTagLeft() {
        return tagLeft;
    }

    public void setTagLeft(boolean tagLeft) {
        this.tagLeft = tagLeft;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Integer[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(Integer[] classLabel) {
        this.classLabel = classLabel;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Integer getTagPrice() {
        return tagPrice;
    }

    public Integer getCheckPrice() {
        return checkPrice;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDone() {
        return done;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public Long getRequesterName() {
        return requesterName;
    }

    public Integer getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    public void setRequesterName(Long requesterName) {
        this.requesterName = requesterName;
    }

    @Override
    public String toString() {
        return "MissionInfoReturnVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", tagPrice=" + tagPrice +
                ", checkPrice=" + checkPrice +
                ", done=" + done +
                ", total=" + total +
                ", totalPoints=" + totalPoints +
                ", publishDate='" + publishDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", classLabel=" + Arrays.toString(classLabel) +
                ", requesterName=" + requesterName +
                ", participantsNum=" + participantsNum +
                ", samplePics=" + Arrays.toString(samplePics) +
                ", tagLeft=" + tagLeft +
                '}';
    }
}

