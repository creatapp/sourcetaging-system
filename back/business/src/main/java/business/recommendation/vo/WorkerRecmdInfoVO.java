package business.recommendation.vo;

import business.common.returnmodel.VO;

import java.io.Serializable;

public class WorkerRecmdInfoVO extends VO implements Serializable {

    private Integer interestRefer;  //兴趣指数
    private Integer traitRefer;     //能力指数

    public WorkerRecmdInfoVO(){}

    public WorkerRecmdInfoVO(Integer interestRefer, Integer traitRefer) {
        this.interestRefer = interestRefer;
        this.traitRefer = traitRefer;
    }

    public Integer getInterestRefer() {
        return interestRefer;
    }

    public void setInterestRefer(Integer interestRefer) {
        this.interestRefer = interestRefer;
    }

    public Integer getTraitRefer() {
        return traitRefer;
    }

    public void setTraitRefer(Integer traitRefer) {
        this.traitRefer = traitRefer;
    }

    @Override
    public String toString() {
        return "WorkerRecmdInfoVO{" +
                ", interestRefer=" + interestRefer +
                ", traitRefer=" + traitRefer +
                '}';
    }
}
