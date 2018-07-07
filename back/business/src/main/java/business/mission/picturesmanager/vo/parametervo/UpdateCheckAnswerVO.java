package business.mission.picturesmanager.vo.parametervo;

public class UpdateCheckAnswerVO {

    private Long missionId;

    private Long authorId;

    private Long checkerId;

    private Long picId;

    private String mark;

    private String tag;

    private boolean checkAnswer;

    private Integer checkTime;

    public UpdateCheckAnswerVO(){}

    public UpdateCheckAnswerVO(Long missionId, Long authorId, Long checkerId, Long picId, String mark, String tag, boolean checkAnswer, Integer checkTime) {
        this.missionId = missionId;
        this.authorId = authorId;
        this.checkerId = checkerId;
        this.picId = picId;
        this.mark = mark;
        this.tag = tag;
        this.checkAnswer = checkAnswer;
        this.checkTime = checkTime;
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

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
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

    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    public Integer getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public String toString() {
        return "UpdateCheckAnswerVO{" +
                "missionId=" + missionId +
                ", authorId=" + authorId +
                ", checkerId=" + checkerId +
                ", picId=" + picId +
                ", mark='" + mark + '\'' +
                ", tag='" + tag + '\'' +
                ", checkAnswer=" + checkAnswer +
                ", checkTime=" + checkTime +
                '}';
    }
}
