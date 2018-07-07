package business.recommendation.core.datamodel;

import java.util.ArrayList;

/*
    用于输入矩阵的构建
 */
public class InputModel {

    public String title;
    public ArrayList<EntityModel>  table;

    public InputModel(String title, ArrayList<EntityModel> table) {
        this.title = title;
        this.table = table;
    }

    public InputModel(ArrayList<EntityModel> table) {
        this.table = table;
    }

    public InputModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<EntityModel> getTable() {
        return table;
    }

    public void setTable(ArrayList<EntityModel> table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "InputModel{" +
                "title='" + title + '\'' +
                ", table=" + table +
                '}';
    }
}
