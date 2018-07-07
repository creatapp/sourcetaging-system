package business.recommendation.core.recommender;

import business.common.entity.workerentity.SimWorkerEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.recommendation.core.datamodel.InputModel;
import business.recommendation.core.datamodel.OutputModel;
import business.recommendation.core.similarities.CosineSimilarity;
import business.recommendation.helper.Recommendation_WorkerDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
* 计算用户相似性，输入是用户特征，该类计算相似性后，将最相似的前10个工人ID写回该工人的数据库中去
* */
@Component
public class UserSimilarityRecommender {

    @Autowired
    private CosineSimilarity cosineSimilarity;

    @Autowired
    private Recommendation_WorkerDbHelper recommendation_workerDbHelper;

    @Transactional
    public void recommend(InputModel inputModel){
        OutputModel outputModel = cosineSimilarity.calcSimilarity(inputModel);
        /*
        * 使用快排进行排序
        * */
        for(int i=0;i<outputModel.similarityModels.size();i++){
            Map<Long,Double> similarities = outputModel.similarityModels.get(i).getSimilarities();
            Set<Long> keys = similarities.keySet();
            Long[] ids = keys.toArray(new Long[keys.size()]);
            int idsSize = ids.length;
            SimilarityValue[] similarityValues = new SimilarityValue[idsSize];
            for (int j = 0; j < idsSize; j++){
                SimilarityValue similarityValue = new SimilarityValue(ids[j],similarities.get(ids[j]));
                similarityValues[j] = similarityValue;
            }
            quickSort(similarityValues,0,similarityValues.length-1);

            //排序完成后选择前10个写回工人数据库
            if(inputModel.getTitle().equals("workerTrait")){
                ArrayList<SimWorkerEntity> recmdTraitSimWorker=new ArrayList<>();
                for(int k=0;k<10&&k<similarityValues.length;k++){
                    recmdTraitSimWorker.add(new SimWorkerEntity(similarityValues[k].getId(),similarityValues[k].getSimilarity()));
                }
                WorkerEntity workerEntity=recommendation_workerDbHelper.findById(outputModel.similarityModels.get(i).getId());
                workerEntity.setRecmdTraitSimWorker(recmdTraitSimWorker);
                recommendation_workerDbHelper.update(workerEntity);
            }
            else{
                ArrayList<SimWorkerEntity> recmdInterestSimWorker=new ArrayList<>();
                for(int k=0;k<10&&k<similarityValues.length;k++){
                    recmdInterestSimWorker.add(new SimWorkerEntity(similarityValues[k].getId(),similarityValues[k].getSimilarity()));
                }
                WorkerEntity workerEntity=recommendation_workerDbHelper.findById(outputModel.similarityModels.get(i).getId());
                workerEntity.setRecmdInterestSimWorker(recmdInterestSimWorker);
                recommendation_workerDbHelper.update(workerEntity);
            }
        }
    }

    private static void quickSort(SimilarityValue[] a, int begin, int end) {

        int tbegin = begin, tend = end;
        // 将第一个值作为快排序的分界值
        double pivot = a[begin].getSimilarity();
        SimilarityValue pivotObj = a[begin];
        while (tbegin < tend) {
            // 如果两个游标没有交集，或者后面一直大于或等于分界值就一直向前移动
            while (tbegin < tend && a[tend].getSimilarity() >= pivot) {
                --tend;
            }
            a[tbegin] = a[tend];
            // 如果两个游标没有交集，或者前面是小于或等于分界值，就一直向后头移动
            while (tbegin < tend && a[tbegin].getSimilarity() <= pivot) {
                ++tbegin;
            }
            a[tend] = a[tbegin];

        }
        // 将临界值赋值给游标的交集的地方
        a[tbegin] = pivotObj;
        if (begin < tend) {
            // 递归排序游标的左边
            quickSort(a, begin, tend - 1);
        }
        if (tbegin < end) {
            // 递归排序游标的右边
            quickSort(a, tbegin + 1, end);
        }

    }
}
