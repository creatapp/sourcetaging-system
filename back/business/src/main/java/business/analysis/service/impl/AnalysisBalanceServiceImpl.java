package business.analysis.service.impl;

import business.analysis.helper.Analysis_MissionDbHelper;
import business.analysis.helper.Analysis_TagDbHelper;
import business.analysis.helper.Analysis_WorkerDbHelper;
import business.analysis.service.AnalysisBalanceService;
import business.analysis.service.utils.AnalysisBalance;
import business.analysis.service.utils.AnalysisUniform;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.tagentity.Check;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.WorkerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AnalysisBalanceServiceImpl implements AnalysisBalanceService {

    @Autowired
    private AnalysisUniform analysisUniform;

    @Autowired
    private AnalysisBalance analysisBalance;

    @Autowired
    private Analysis_TagDbHelper tagDbHelper;

    @Autowired
    private Analysis_WorkerDbHelper workerDbHelper;

    @Autowired
    private Analysis_MissionDbHelper missionDbHelper;

    @Deprecated
    public void startAnalysis() {
        //获取标注数据
        //获取工人数据
        //获取任务数据
        //对任务难度进行建模
        //对工人能力进行建模

        //整合得出答案【对还是错】

        //修改任务数据
        //修改工人数据
        //生成给发包者和工人的报告
    }

    @Override
    @Async("taskExecutor")
    @Transactional
    public void  planToBalance(Long[] tagIdArray) {
        //首先整合得到结果
        //然后进行结算

        for (int tagIdx = 0; tagIdx < tagIdArray.length; tagIdx++){
            TagEntity tagEntity = tagDbHelper.findById(tagIdArray[tagIdx]);
            MissionEntity missionEntity = missionDbHelper.findById(tagEntity.getMissionId());
            Integer[] labelArray =  missionEntity.getLabel().toArray(new Integer[missionEntity.getAssignedTagSet().size()]);
            int labelSize = labelArray.length;


            Double[] timeArray = new Double[6];
            Integer[][] workerTraitArray = new Integer[6][labelSize];

            WorkerEntity[] workerEntities = new WorkerEntity[6];
            //标注者
            Long tagWorkerId = tagEntity.getWorkerId();
            WorkerEntity tagWorker = workerDbHelper.findById(tagWorkerId);
            timeArray[0] = (double)(tagEntity.getPictureTime() / 1000);
            workerEntities[0] = tagWorker;

            for (int i = 0;i < labelSize; i++){
                workerTraitArray[0][i] = tagWorker.getWorkerInfo().get(labelArray[i]).getTraitRefer();
            }

            //检查者
            Long checkWorkersIdArray[] = new Long[5];
            Boolean workerTagArray[] = new Boolean[6];
            workerTagArray[0] = true;//标注者相当于检查正确true
            Check[] checks = tagEntity.getChecks();
            for (int i = 0; i < checks.length; i++){
                checkWorkersIdArray[i] = checks[i].getWorkerId();
                workerTagArray[i+1] = checks[i].isJudge();
                timeArray[i+1] = (double) (checks[i].getCheckTime() / 1000);

                WorkerEntity checkWorker = workerDbHelper.findById(checks[i].getWorkerId());
                workerEntities[i+1] = checkWorker;
                for (int j = 0; j < labelSize; j++){
                    workerTraitArray[i+1][j] = checkWorker.getWorkerInfo().get(labelArray[j]).getTraitRefer();
                }
            }

            //整合
            boolean uniformResult = analysisUniform.getAnswer(workerTraitArray,workerTagArray,timeArray,missionEntity);

            //开始结算
            analysisBalance.balance(uniformResult,missionEntity,tagEntity,workerEntities);
        }
    }
}
