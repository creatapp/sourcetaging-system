package business.recommendation.core.datamodel;

/*
* 相似性算法输出的类
* */


import java.util.Map;
public class SimilarityModel {

    public Long id;//基准

    public Map<Long,Double> similarities;// 其他工人：相似度

    public SimilarityModel(Long id, Map<Long, Double> similarities) {
        this.id = id;
        this.similarities = similarities;
    }

    public SimilarityModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Double> getSimilarities() {
        return similarities;
    }

    public void setSimilarities(Map<Long, Double> similarities) {
        this.similarities = similarities;
    }

    @Override
    public String toString() {
        return "SimilarityModel{" +
                "id=" + id +
                ", similarities=" + similarities +
                '}';
    }
}
