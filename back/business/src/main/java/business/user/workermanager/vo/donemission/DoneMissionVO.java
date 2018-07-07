package business.user.workermanager.vo.donemission;

import business.common.returnmodel.VO;

import java.io.Serializable;
import java.util.Arrays;

public class DoneMissionVO extends VO implements Serializable {

    //common
    private Long id;

    private String title;

    private String description;

    private String type;

    private String kind;//check or tag

    private Integer[] classLabel;


    //personal
    private Integer right;//number of missions that had been correctly done

    private Integer wrong;

    private Integer toBeJudge;

    private Integer point;

    public DoneMissionVO(Long id, String title, String description, String type,
                         String kind, Integer right, Integer wrong, Integer toBeJudge,
                         Integer point,Integer[] classLabel) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.kind = kind;
        this.right = right;
        this.wrong = wrong;
        this.toBeJudge = toBeJudge;
        this.point = point;
        this.classLabel=classLabel;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public Integer getToBeJudge() {
        return toBeJudge;
    }

    public void setToBeJudge(Integer toBeJudge) {
        this.toBeJudge = toBeJudge;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(Integer[] classLabel) {
        this.classLabel = classLabel;
    }

    @Override
    public String toString() {
        return "DoneMissionVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", right=" + right +
                ", wrong=" + wrong +
                ", toBeJudge=" + toBeJudge +
                ", point=" + point +
                ", classLabel=" + Arrays.toString(classLabel) +
                '}';
    }
}
