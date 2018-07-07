package business.recommendation.core.recommender;

/*
* 本类是id：相似度的类
* */
public class SimilarityValue {
    private Long id;
    private Double similarity;

    public SimilarityValue(Long id, Double similarity) {
        this.id = id;
        this.similarity = similarity;
    }

    public SimilarityValue(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "SimilarityValue{" +
                "id=" + id +
                ", similarity=" + similarity +
                '}';
    }
}
