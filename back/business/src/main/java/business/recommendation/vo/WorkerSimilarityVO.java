package business.recommendation.vo;

import business.common.returnmodel.VO;

import java.io.Serializable;

public class WorkerSimilarityVO extends VO implements Serializable {

    private Long workerId;
    private String nickName;
    private Integer ranking;
    private Integer similarityType;  //相似原因：0--兴趣 1--能力
    private Double similarityDegree;  //相似度

    public WorkerSimilarityVO(){}

    public WorkerSimilarityVO(Long workerId, String nickName, Integer ranking, Integer similarityType, Double similarityDegree) {
        this.workerId = workerId;
        this.nickName = nickName;
        this.ranking = ranking;
        this.similarityType = similarityType;
        this.similarityDegree = similarityDegree;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getSimilarityType() {
        return similarityType;
    }

    public void setSimilarityType(Integer similarityType) {
        this.similarityType = similarityType;
    }

    public Double getSimilarityDegree() {
        return similarityDegree;
    }

    public void setSimilarityDegree(Double similarityDegree) {
        this.similarityDegree = similarityDegree;
    }

    @Override
    public String toString() {
        return "WorkerSimilarityVO{" +
                "workerId=" + workerId +
                ", nickName='" + nickName + '\'' +
                ", ranking=" + ranking +
                ", similarityType=" + similarityType +
                ", similarityDegree=" + similarityDegree +
                '}';
    }
}
