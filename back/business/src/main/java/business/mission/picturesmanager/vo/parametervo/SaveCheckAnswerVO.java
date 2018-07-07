package business.mission.picturesmanager.vo.parametervo;

public class SaveCheckAnswerVO {

    private Long missionId;

    private Long authorId;

    private Long picId;

    private boolean checkAnswer;

    private Integer picTime;

    public SaveCheckAnswerVO(){}

    public SaveCheckAnswerVO(Long missionId, Long authorId, Long picId, boolean checkAnswer, Integer picTime) {
        this.missionId = missionId;
        this.authorId = authorId;
        this.picId = picId;
        this.checkAnswer = checkAnswer;
        this.picTime = picTime;
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

    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    public Integer getPicTime() {
        return picTime;
    }

    public void setPicTime(Integer picTime) {
        this.picTime = picTime;
    }

    @Override
    public String toString() {
        return "SaveCheckAnswerVO{" +
                "missionId=" + missionId +
                ", authorId=" + authorId +
                ", picId=" + picId +
                ", checkAnswer=" + checkAnswer +
                ", picTime=" + picTime +
                '}';
    }
}

