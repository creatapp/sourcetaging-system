package business.analysis.service.utils;

import business.common.entity.missionentity.MissionEntity;
import org.springframework.stereotype.Component;

@Component
public class AnalysisUniform {

    /*
    * workerTraitArray 已经按照任务标签筛选好的能力数组  6*labelNum  1个标注者5个检查者的labelNum个标签下的能力指数
    * workerJudge  检查者的检查判断 6
    * taskTime 标注和检查的时间 6
    * taskHard 任务难度
    *
    * */
    public boolean getAnswer(Integer[][] workerTraitArray, Boolean[] checkJudge , Double[] taskTime, MissionEntity missionEntity){

        //首先认为本次整合的结果应该是工人的能力权重说了算，则每一个工人都会根据其能力指数（各标签下的能力的平均值）占总体的比率得到对任务评判的权值

        int labelSize = workerTraitArray[0].length;
        double[] workerAvgTrait = new double[6];
        double totalTrait = 0;
        for (int i = 0; i < workerTraitArray.length; i++){
            double tmp = 0;
            for (int j = 0; j < labelSize; j++){
                tmp += workerTraitArray[i][j] < 1 ? 1 : workerTraitArray[i][j];
            }
            workerAvgTrait[i] = tmp/labelSize;
            totalTrait += workerAvgTrait[i];
        }
        double[] workerWeigh = new double[6];
        for (int i = 0; i < workerTraitArray.length; i++){
            workerWeigh[i] = workerAvgTrait[i] / totalTrait;
        }

        /*
        * 进行时间噪音处理，将偏离总体的时间设为平均值进行计算
        * */
        dealNoise(taskTime);

        /*
        * 计算任务加权总耗时 taskWeighTime = Σ workerWeigh[i] * taskTime[i]  [检查者]   + 标注者的时间
        *
        * */
        double taskWeighTime = 0;
        for (int i = 0; i < workerWeigh.length; i++){
            taskWeighTime += workerWeigh[i] * taskTime[i];
        }

        /*任务的答案的差异性  Gini Impurity
        * 基尼不纯度越小，纯度越高，集合的有序程度越高，分类的效果越好；
        * */
        double gini = 0;
        int trueTagNum = 0;
        for (int i = 0; i < checkJudge.length; i++){
            if (checkJudge[i])trueTagNum++;
        }
        gini = 1 - ((float)trueTagNum/5)*((float)trueTagNum/5) - ((float)(1-trueTagNum/5))*((float)(1-trueTagNum/5));

        /*
        * 定义本次的任务难度  thisTaskHard = logistic(taskWeighTime * gini)
        * */
        double thisTaskHard = 1 / (1 + Math.exp(-(taskWeighTime * gini)));
        double taskHard = missionEntity.getDifficulty();

        //如果这是第一次，则计算出来的任务难度就是最终结果，否则任务难度是上一次的60%和这一次的40%
        if (taskHard <= 0){
            taskHard = thisTaskHard;
        }else {
            taskHard = 0.6 * taskHard + 0.4 * thisTaskHard;
        }
        missionEntity.setDifficulty(taskHard);


        /*
         * 调整权值，以上统计了能力权重，将所有权值前3的权值调高到原来的（1+taskHard），后面三个调低到原来的（1-taskHard）
         * */
        for (int i = 0; i < workerWeigh.length; i++){
            for (int j = i; j < 5; j++){
                if (workerWeigh[j+1] > workerWeigh[j]){
                    double tmp = workerWeigh[j];
                    workerWeigh[j] = workerWeigh[j+1];
                    workerWeigh[j+1] = tmp;
                    boolean tmpp = checkJudge[j];
                    checkJudge[j] = checkJudge[j+1];
                    checkJudge[j+1] = tmpp;
                }
            }
        }
        for (int i = 0; i < workerWeigh.length; i++){
            if (i < 3){
                workerWeigh[i] *= (1 + taskHard);
            }else{
                workerWeigh[i] *= (1-taskHard);
            }
        }

        /*
        * 计算结果加权和  resultWeigh = Σ true:1 false:-1  * workerWeigh
        * */
        double resultWeigh = 0;
        for (int i = 0; i < workerWeigh.length; i++){
            if (checkJudge[i]){
                resultWeigh += workerWeigh[i];
            }else{
                resultWeigh -= workerWeigh[i];
            }
        }

        // > 0 说明判断正确的工人的总权值大
        if (resultWeigh > 0)return true;
        else return false;
    }


    private void dealNoise(Double[] taskTime){

        //只能排除检查者中的噪音

        int size = taskTime.length;
        double avg = 0.0;
        double var = 0.0;

        for (int i = 1; i < size; i++){
            avg += taskTime[i];
        }
        avg /= (size - 1);

        for (int i = 1; i < size; i++){
            var += ((taskTime[i] - avg) * (taskTime[i] - avg));
        }
        var /= (size - 1);

        double svar = Math.sqrt(var);

        //调整落在（μ-3Σ， μ+3Σ）之外的数据为平均值
        double lower = avg - 3 * svar;
        double upper = avg + 3 * svar;

        for (int i = 1; i < size; i++){
            if (taskTime[i] < lower || taskTime[i] > upper){
                taskTime[i] = avg;
            }
        }

    }

}
