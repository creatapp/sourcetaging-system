package business.common.entity.tagentity;

import java.io.Serializable;

public class Tag implements Serializable {

    private Long workerId;

    private String mark;

    private String comment;

    private Integer tagTime;

    public Tag(){}

    public Tag(Long workerId, String mark, String comment, Integer tagTime) {
        this.workerId = workerId;
        this.mark = mark;
        this.comment = comment;
        this.tagTime = tagTime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTagTime() {
        return tagTime;
    }

    public void setTagTime(Integer tagTime) {
        this.tagTime = tagTime;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "workerId=" + workerId +
                ", mark='" + mark + '\'' +
                ", comment='" + comment + '\'' +
                ", tagTime=" + tagTime +
                '}';
    }
}
