package business.mission.picturesmanager.vo.parametervo;

public class SaveTagAnswerVO {

    private Long missionId;

    private Long authorId;

    private Long picId;

    private Long picTime;

    private String mark;

    private String tag;

    private Integer tagTime;

    public SaveTagAnswerVO(){}

    public SaveTagAnswerVO(Long missionId, Long authorId, Long picId, Long picTime, String mark, String tag, Integer tagTime) {
        this.missionId = missionId;
        this.authorId = authorId;
        this.picId = picId;
        this.picTime = picTime;
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

    public Long getPicTime() {
        return picTime;
    }

    public void setPicTime(Long picTime) {
        this.picTime = picTime;
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
        return "SaveTagAnswerVO{" +
                "missionId=" + missionId +
                ", authorId=" + authorId +
                ", picId=" + picId +
                ", picTime=" + picTime +
                ", mark='" + mark + '\'' +
                ", tag='" + tag + '\'' +
                ", tagTime=" + tagTime +
                '}';
    }
}
