package business.user.usermanager.vo.workermanagervo;

import business.common.entity.workerentity.WorkerEntity;
import business.common.entity.workerentity.WorkerInfoEntity;
import business.common.returnmodel.VO;

import javax.persistence.*;
import java.io.Serializable;

public class WorkerDataVO extends VO implements Serializable {

    private Integer rank;


    //任务信息
    private Integer doingMissionsNum;
    @Lob()
    private Long[] doingMissionIds;

    private Integer doneMissionNum;
    @Lob()
    private Long[] doneMissionIds;


    //标注信息
    private Integer totalAnswerNum;

    private Integer rightAnswerNum;//总正确数

    private Integer wrongAnswerNum;//总错误数

    private Integer toBeJudgeAnswerNum;

    private Double answerAccuracy;//总正确率

    private WorkerInfoEntity[] workerInfo;

    //检查信息
    private Integer totalCheckNum;

    private Integer rightCheckNum;

    private Integer wrongCheckNum;

    private Integer toBeJudgeCheckNum;

    private Double checkAccuracy;

    public WorkerDataVO(WorkerEntity workerEntity) {
        this.rank=workerEntity.getRank();
        this.workerInfo=new WorkerInfoEntity[workerEntity.getWorkerInfo().size()];
        for(int i=0;i<workerEntity.getWorkerInfo().size();i++){
            this.workerInfo[i]=workerEntity.getWorkerInfo().get(i);
        }
        this.doingMissionsNum=workerEntity.getDoingMissionsNum();
        this.doneMissionNum=workerEntity.getDoneMissionNum();


        this.doingMissionIds=new Long[this.getDoingMissionsNum()];
        for(int i=0;i<doingMissionsNum;i++){
            this.doingMissionIds[i]=workerEntity.getDoingMissionEntities()[i].getMissionId();
        }

        this.doneMissionIds=new Long[this.getDoneMissionNum()];
        for(int i=0;i<doneMissionNum;i++){
            this.doneMissionIds[i]=workerEntity.getDoneMissionEntities()[i].getMissionId();
        }
        this.answerAccuracy=workerEntity.getAnswerAccuracy();
        this.checkAccuracy=workerEntity.getCheckAccuracy();
        this.rightAnswerNum=workerEntity.getRightAnswerNum();
        this.rightCheckNum=workerEntity.getRightCheckNum();
        this.toBeJudgeAnswerNum=workerEntity.getToBeJudgeAnswerNum();
        this.toBeJudgeCheckNum=workerEntity.getToBeJudgeCheckNum();
        this.totalAnswerNum=workerEntity.getTotalAnswerNum();
        this.totalCheckNum=workerEntity.getTotalCheckNum();
        this.wrongAnswerNum=workerEntity.getWrongAnswerNum();
        this.wrongCheckNum=workerEntity.getWrongCheckNum();
    }

    public WorkerDataVO() {
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getDoingMissionsNum() {
        return doingMissionsNum;
    }

    public void setDoingMissionsNum(Integer doingMissionsNum) {
        this.doingMissionsNum = doingMissionsNum;
    }

    public Long[] getdoingMissionIds() {
        return doingMissionIds;
    }

    public void setdoingMissionIds(Long[] doingMissionIds) {
        this.doingMissionIds = doingMissionIds;
    }

    public Integer getDoneMissionNum() {
        return doneMissionNum;
    }

    public void setDoneMissionNum(Integer doneMissionNum) {
        this.doneMissionNum = doneMissionNum;
    }

    public Long[] getdoneMissionIds() {
        return doneMissionIds;
    }

    public void setdoneMissionIds(Long[] doneMissionIds) {
        this.doneMissionIds = doneMissionIds;
    }

    public Integer getTotalAnswerNum() {
        return totalAnswerNum;
    }

    public void setTotalAnswerNum(Integer totalAnswerNum) {
        this.totalAnswerNum = totalAnswerNum;
    }

    public Integer getRightAnswerNum() {
        return rightAnswerNum;
    }

    public void setRightAnswerNum(Integer rightAnswerNum) {
        this.rightAnswerNum = rightAnswerNum;
    }

    public Integer getWrongAnswerNum() {
        return wrongAnswerNum;
    }

    public void setWrongAnswerNum(Integer wrongAnswerNum) {
        this.wrongAnswerNum = wrongAnswerNum;
    }

    public Integer getToBeJudgeAnswerNum() {
        return toBeJudgeAnswerNum;
    }

    public void setToBeJudgeAnswerNum(Integer toBeJudgeAnswerNum) {
        this.toBeJudgeAnswerNum = toBeJudgeAnswerNum;
    }

    public Double getAnswerAccuracy() {
        return answerAccuracy;
    }

    public void setAnswerAccuracy(Double answerAccuracy) {
        this.answerAccuracy = answerAccuracy;
    }

    public WorkerInfoEntity[] getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerInfoEntity[] workerInfo) {
        this.workerInfo = workerInfo;
    }

    public Integer getTotalCheckNum() {
        return totalCheckNum;
    }

    public void setTotalCheckNum(Integer totalCheckNum) {
        this.totalCheckNum = totalCheckNum;
    }

    public Integer getRightCheckNum() {
        return rightCheckNum;
    }

    public void setRightCheckNum(Integer rightCheckNum) {
        this.rightCheckNum = rightCheckNum;
    }

    public Integer getWrongCheckNum() {
        return wrongCheckNum;
    }

    public void setWrongCheckNum(Integer wrongCheckNum) {
        this.wrongCheckNum = wrongCheckNum;
    }

    public Integer getToBeJudgeCheckNum() {
        return toBeJudgeCheckNum;
    }

    public void setToBeJudgeCheckNum(Integer toBeJudgeCheckNum) {
        this.toBeJudgeCheckNum = toBeJudgeCheckNum;
    }

    public Double getCheckAccuracy() {
        return checkAccuracy;
    }

    public void setCheckAccuracy(Double checkAccuracy) {
        this.checkAccuracy = checkAccuracy;
    }
}
