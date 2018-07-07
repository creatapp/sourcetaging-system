package business.mission.picturesmanager.vo;

import business.common.returnmodel.VO;

import java.io.Serializable;

public class PictureReturnVO extends VO implements Serializable {

    private Long authorId;
    private Long picId;
    private String picData;
    private String mark="";
    private String tag="";
    private Integer left=0;

    public PictureReturnVO(){

    }

    public PictureReturnVO(Long authorId, Long picId,String picData, String mark, String tag, Integer left){
        this.authorId=authorId;
        this.picId = picId;
        this.mark=mark;
        this.tag=tag;
        this.picData=picData;
        this.left=left;
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

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public String getPicData() {
        return picData;
    }

    public void setPicData(String picData) {
        this.picData = picData;
    }

    @Override
    public String toString() {
        return "PictureReturnVO{" +
                "authorId=" + authorId +
                ", picId=" + picId +
                ", picData='" + picData + '\'' +
                ", mark='" + mark + '\'' +
                ", tag='" + tag + '\'' +
                ", left=" + left +
                '}';
    }
}
