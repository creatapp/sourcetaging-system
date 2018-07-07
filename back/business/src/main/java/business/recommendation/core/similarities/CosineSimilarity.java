package business.recommendation.core.similarities;

import business.recommendation.core.datamodel.EntityModel;
import business.recommendation.core.datamodel.InputModel;
import business.recommendation.core.datamodel.OutputModel;
import business.recommendation.core.datamodel.SimilarityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
* 计算两个向量的余弦距离
* */

@Component
public class CosineSimilarity {

    public OutputModel calcSimilarity(InputModel inputModel){

        ArrayList<EntityModel> entityModels = inputModel.getTable();
        int size = entityModels.size();
        ArrayList<SimilarityModel> similarityModels = new ArrayList<>();

        for (int i = 0; i < size; i++){ //比较者
            SimilarityModel cur = new SimilarityModel();
            Map<Long,Double> map = new HashMap<>();
            cur.setId(entityModels.get(i).id);

            for (int j = 0; j < size; j++){//参照物
                if (j < i){//将前面计算的结果加入
                    SimilarityModel prev = similarityModels.get(j);
                    double sim = prev.similarities.get(cur.id);
                    map.put(prev.id,sim);
                }else if (j == i){continue;}
                else if (j > i){
                    double sim = calcCosine(entityModels.get(i),entityModels.get(j));
                    map.put(entityModels.get(j).id,sim);
                }
            }
            cur.setSimilarities(map);
            similarityModels.add(cur);
        }
        return new OutputModel(inputModel.title,similarityModels);
    }

    /*
    * cos(θ) = (ΣAi * Bi) / (√ΣAi^2 * √ΣBi^2)
    * */
    public Double calcCosine(EntityModel entityModel_1, EntityModel entityModel_2){
        ArrayList<Double> vector1 = entityModel_1.row; int vec1_size = vector1.size();
        ArrayList<Double> vector2 = entityModel_2.row;

        if (vec1_size != vector2.size()){throw  new RuntimeException("size not the same");}

        long aibi = 0; long ai2 = 0; long bi2 = 0;
        for (int i = 0; i < vec1_size; ++ i){
            double ai = vector1.get(i);
            double bi = vector2.get(i);
            aibi += ai * bi;
            ai2 += (ai * ai);
            bi2 += (bi * bi);
        }

        return (double)aibi / (Math.sqrt(ai2) * Math.sqrt(bi2));
    }
}
