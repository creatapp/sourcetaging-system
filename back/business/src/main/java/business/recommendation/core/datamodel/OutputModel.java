package business.recommendation.core.datamodel;


import java.util.ArrayList;
import java.util.Map;

public class OutputModel {

    public String title;

    public ArrayList<SimilarityModel> similarityModels;

    public OutputModel(String title, ArrayList<SimilarityModel> similarityModels) {
        this.title = title;
        this.similarityModels = similarityModels;
    }

    public OutputModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SimilarityModel> getSimilarityModels() {
        return similarityModels;
    }

    public void setSimilarityModels(ArrayList<SimilarityModel> similarityModels) {
        this.similarityModels = similarityModels;
    }

    @Override
    public String toString() {
        return "OutputModel{" +
                "title='" + title + '\'' +
                ", similarityModels=" + similarityModels +
                '}';
    }
}
