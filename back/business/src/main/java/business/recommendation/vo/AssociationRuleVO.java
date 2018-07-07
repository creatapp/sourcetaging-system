package business.recommendation.vo;

import business.common.returnmodel.VO;

import java.io.Serializable;

public class AssociationRuleVO extends VO implements Serializable {

    private String arg1;   //潜在挖掘项
    private Double arg2;
    private Double supportDegree;   //支持度
    private Double convinceDegree;  //置信区
    private Double ascendDegree;     //提升度

    public AssociationRuleVO(){}

    public AssociationRuleVO(String arg1, Double arg2, Double supportDegree, Double convinceDegree, Double ascendDegree) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.supportDegree = supportDegree;
        this.convinceDegree = convinceDegree;
        this.ascendDegree = ascendDegree;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public Double getArg2() {
        return arg2;
    }

    public void setArg2(Double arg2) {
        this.arg2 = arg2;
    }

    public Double getSupportDegree() {
        return supportDegree;
    }

    public void setSupportDegree(Double supportDegree) {
        this.supportDegree = supportDegree;
    }

    public Double getConvinceDegree() {
        return convinceDegree;
    }

    public void setConvinceDegree(Double convinceDegree) {
        this.convinceDegree = convinceDegree;
    }

    public Double getAscendDegree() {
        return ascendDegree;
    }

    public void setAscendDegree(Double ascendDegree) {
        this.ascendDegree = ascendDegree;
    }

    @Override
    public String toString() {
        return "AssociationRuleVO{" +
                "arg1='" + arg1 + '\'' +
                ", arg2=" + arg2 +
                ", supportDegree=" + supportDegree +
                ", convinceDegree=" + convinceDegree +
                ", ascendDegree=" + ascendDegree +
                '}';
    }
}
