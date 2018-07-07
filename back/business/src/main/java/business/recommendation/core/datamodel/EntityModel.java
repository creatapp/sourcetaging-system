package business.recommendation.core.datamodel;

import java.util.ArrayList;

public class EntityModel {
    /*本类是每一条数据*/
    public Long id;
    public ArrayList<Double> row;

    public EntityModel(Long id, ArrayList<Double> row) {
        this.id = id;
        this.row = row;
    }

    public EntityModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Double> getRow() {
        return row;
    }

    public void setRow(ArrayList<Double> row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "EntityModel{" +
                "id=" + id +
                ", row=" + row +
                '}';
    }
}
