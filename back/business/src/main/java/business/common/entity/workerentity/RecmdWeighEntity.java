package business.common.entity.workerentity;

import javax.persistence.*;

@Entity
@Table(name = "recmdweigh")
public class RecmdWeighEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double hotRecmd;//热门推荐 --参与人数最多的任务【实时】

    private Double coldRecmd;//冷门推荐【实时】

    private Double traitRecmd;//相似能力用户任务推荐【离线】

    private Double interestRecmd;//相似兴趣用户任务推荐【离线】

    private Double labelRecmd;//相同主题任务推荐【实时】

    private Double relationRecmd;//任务关联项挖掘推荐【离线】

    public RecmdWeighEntity(Double hotRecmd, Double coldRecmd, Double traitRecmd, Double interestRecmd, Double labelRecmd, Double relationRecmd) {
        this.hotRecmd = hotRecmd;
        this.coldRecmd = coldRecmd;
        this.traitRecmd = traitRecmd;
        this.interestRecmd = interestRecmd;
        this.labelRecmd = labelRecmd;
        this.relationRecmd = relationRecmd;
    }

    public RecmdWeighEntity() {
        this.hotRecmd=1.0;
        this.coldRecmd=0.0;
        this.interestRecmd=0.0;
        this.labelRecmd=0.0;
        this.relationRecmd=0.0;
        this.traitRecmd=0.0;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getHotRecmd() {
        return hotRecmd;
    }

    public void setHotRecmd(Double hotRecmd) {
        this.hotRecmd = hotRecmd;
    }

    public Double getColdRecmd() {
        return coldRecmd;
    }

    public void setColdRecmd(Double coldRecmd) {
        this.coldRecmd = coldRecmd;
    }

    public Double getTraitRecmd() {
        return traitRecmd;
    }

    public void setTraitRecmd(Double traitRecmd) {
        this.traitRecmd = traitRecmd;
    }

    public Double getInterestRecmd() {
        return interestRecmd;
    }

    public void setInterestRecmd(Double interestRecmd) {
        this.interestRecmd = interestRecmd;
    }

    public Double getLabelRecmd() {
        return labelRecmd;
    }

    public void setLabelRecmd(Double labelRecmd) {
        this.labelRecmd = labelRecmd;
    }

    public Double getRelationRecmd() {
        return relationRecmd;
    }

    public void setRelationRecmd(Double relationRecmd) {
        this.relationRecmd = relationRecmd;
    }

    @Override
    public String toString() {
        return "RecmdWeighEntity{" +
                "id=" + id +
                ", hotRecmd=" + hotRecmd +
                ", coldRecmd=" + coldRecmd +
                ", traitRecmd=" + traitRecmd +
                ", interestRecmd=" + interestRecmd +
                ", labelRecmd=" + labelRecmd +
                ", relationRecmd=" + relationRecmd +
                '}';
    }
}
