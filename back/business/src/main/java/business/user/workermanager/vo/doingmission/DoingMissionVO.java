package business.user.workermanager.vo.doingmission;

import business.common.returnmodel.VO;

import java.io.Serializable;
import java.util.Arrays;

public class DoingMissionVO extends VO implements Serializable {

    //common
    private Long id;//missionId

    private String title;

    private String description;

    private String type;//标注类型

    private String kind;//check or tag

    private Integer price;

    //personal
    private Integer done; //number of mission that has been done

    private Integer total;

    private Integer[] classLabel;


    public DoingMissionVO(Long id, String title, String description, String type,
                          String kind, Integer price,Integer done, Integer total,Integer[] classLabel) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.kind = kind;
        this.price=price;
        this.done = done;
        this.total = total;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(Integer[] classLabel) {
        this.classLabel = classLabel;
    }

    @Override
    public String toString() {
        return "DoingMissionVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", price=" + price +
                ", done=" + done +
                ", total=" + total +
                ", classLabel=" + Arrays.toString(classLabel) +
                '}';
    }
}
