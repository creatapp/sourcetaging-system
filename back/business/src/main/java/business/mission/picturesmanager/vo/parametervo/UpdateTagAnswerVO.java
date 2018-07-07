package business.mission.picturesmanager.vo.parametervo;

public class UpdateTagAnswerVO {
    private Long missionId;
    private Long authorId;
    private Long picId;
    private String mark;
    private String tag;
    private Integer tagTime;

    public UpdateTagAnswerVO(){}

    public UpdateTagAnswerVO(Long missionId, Long authorId, Long picId, String mark, String tag, Integer tagTime) {
        this.missionId = missionId;
        this.authorId = authorId;
        this.picId = picId;
        this.mark = mark;
        this.tag = tag;
        this.tagTime = tagTime;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTagTime() {
        return tagTime;
    }

    public void setTagTime(Integer tagTime) {
        this.tagTime = tagTime;
    }

    @Override
    public String toString() {
        return "UpdateTagAnswerVO{" +
                "missionId=" + missionId +
                ", authorId=" + authorId +
                ", picId=" + picId +
                ", mark='" + mark + '\'' +
                ", tag='" + tag + '\'' +
                ", tagTime=" + tagTime +
                '}';
    }
}
