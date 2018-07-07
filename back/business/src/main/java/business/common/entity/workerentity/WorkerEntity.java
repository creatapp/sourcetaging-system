package business.common.entity.workerentity;

import business.common.entity.FatherEntity;
import business.common.entity.missionentity.RecmdMissionEntity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Transactional
@Entity
@Table(name = "worker")
public class WorkerEntity extends FatherEntity implements Serializable {

        //基本信息
        @Id
        private long workerId;

        private Integer rank;

        private Integer points;


        //任务信息
        private Integer doingMissionsNum;
        @Lob()
        private DoingMissionEntity[] doingMissionEntities;

        private Integer doneMissionNum;
        @Lob()
        private DoneMissionEntity[] doneMissionEntities;

        @ElementCollection(fetch = FetchType.LAZY)//使用此注解配置集合映射关联
        private List<RecmdMissionEntity> label_recmdMissionEntityList;  //基于相同主题的任务

        @ElementCollection(fetch = FetchType.LAZY)//使用此注解配置集合映射关联
        private List<RecmdMissionEntity> tail_recmdMissionEntityList;  //基于关联项挖掘，从用户做过的任务中挖掘用户的喜好

        @ElementCollection(fetch = FetchType.LAZY)//使用此注解配置集合映射关联
        private List<RecmdMissionEntity> userInterest_recmdMissionEntityList;  //基于用户兴趣相似性，推荐相似用户做过的任务

        @ElementCollection(fetch = FetchType.LAZY)//使用此注解配置集合映射关联
        private List<RecmdMissionEntity> userTrait_recmdMissionEntityList;   //基于用户能力相似性，推荐相似用户做过的任务

        @ElementCollection(fetch = FetchType.LAZY)//使用此注解配置集合映射关联
        private List<Long> uninterestedList;//用户不感兴趣的missionId

        @OneToOne(targetEntity = RecmdWeighEntity.class,cascade = CascadeType.PERSIST)
        @JoinColumn(name="recmd_weigh_id",referencedColumnName = "id")
        private RecmdWeighEntity recmdWeighEntity;


        //标注信息
        private Integer totalAnswerNum;

        private Integer rightAnswerNum;//总正确数

        private Integer wrongAnswerNum;//总错误数

        private Integer toBeJudgeAnswerNum;

        private Double answerAccuracy;//总正确率

        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JoinColumn(name="worker_id")
        private Map<Integer,WorkerInfoEntity> workerInfo;

        //检查信息
        private Integer totalCheckNum;

        private Integer rightCheckNum;

        private Integer wrongCheckNum;

        private Integer toBeJudgeCheckNum;

        private Double checkAccuracy;

        //相似工人id组
        @ElementCollection(fetch = FetchType.LAZY,targetClass = SimWorkerEntity.class)//使用此注解配置集合映射关联
        private List<SimWorkerEntity> recmdTraitSimWorker;

        @ElementCollection(fetch = FetchType.LAZY,targetClass = SimWorkerEntity.class)//使用此注解配置集合映射关联
        private List<SimWorkerEntity> recmdInterestSimWorker;


        public WorkerEntity(Integer rank, Integer points, Integer doingMissionsNum,
                        DoingMissionEntity[] doingMissionEntities, Integer doneMissionNum,
                        DoneMissionEntity[] doneMissionEntities,
                        Integer totalAnswerNum, Integer rightAnswerNum, Integer wrongAnswerNum,
                        Integer toBeJudgeAnswerNum, Double answerAccuracy, Map<Integer, WorkerInfoEntity> workerInfo,
                        Integer totalCheckNum, Integer rightCheckNum, Integer wrongCheckNum,
                        Integer toBeJudgeCheckNum,Double checkAccuracy) {
        this.rank = rank;
        this.points = points;
        this.doingMissionsNum = doingMissionsNum;
        this.doingMissionEntities = doingMissionEntities;
        this.doneMissionNum = doneMissionNum;
        this.doneMissionEntities = doneMissionEntities;
        this.totalAnswerNum = totalAnswerNum;
        this.rightAnswerNum = rightAnswerNum;
        this.wrongAnswerNum = wrongAnswerNum;
        this.toBeJudgeAnswerNum = toBeJudgeAnswerNum;
        this.answerAccuracy = answerAccuracy;
        this.workerInfo = workerInfo;
        this.totalCheckNum = totalCheckNum;
        this.rightCheckNum = rightCheckNum;
        this.wrongCheckNum = wrongCheckNum;
        this.toBeJudgeCheckNum = toBeJudgeCheckNum;
        this.checkAccuracy = checkAccuracy;
        this.recmdWeighEntity = new RecmdWeighEntity();
        this.label_recmdMissionEntityList=new ArrayList<>();
        this.tail_recmdMissionEntityList=new ArrayList<>();
        this.userInterest_recmdMissionEntityList=new ArrayList<>();
        this.userTrait_recmdMissionEntityList=new ArrayList<>();
        this.uninterestedList=new ArrayList<>();
        this.recmdInterestSimWorker=new ArrayList<>();
        this.recmdTraitSimWorker=new ArrayList<>();
        this.recmdWeighEntity=new RecmdWeighEntity();
    }

    public WorkerEntity(){
        this.rank = 0;
        this.points = 0;
        this.doingMissionsNum = 0;
        this.doingMissionEntities = new DoingMissionEntity[4];
        this.doneMissionNum = 0;
        this.doneMissionEntities = new DoneMissionEntity[88];
        this.totalAnswerNum = 0;
        this.rightAnswerNum = 0;
        this.wrongAnswerNum = 0;
        this.toBeJudgeAnswerNum = 0;
        this.answerAccuracy = 0.0;
        this.workerInfo = new HashMap<>();
        this.totalCheckNum = 0;
        this.rightCheckNum = 0;
        this.wrongCheckNum = 0;
        this.toBeJudgeCheckNum = 0;
        this.checkAccuracy = 0.0;

        this.recmdWeighEntity = new RecmdWeighEntity();
        this.label_recmdMissionEntityList=new ArrayList<>();
        this.tail_recmdMissionEntityList=new ArrayList<>();
        this.userInterest_recmdMissionEntityList=new ArrayList<>();
        this.userTrait_recmdMissionEntityList=new ArrayList<>();
        this.uninterestedList=new ArrayList<>();
        this.recmdInterestSimWorker=new ArrayList<>();
        this.recmdTraitSimWorker=new ArrayList<>();
        this.recmdWeighEntity=new RecmdWeighEntity();
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<SimWorkerEntity> getRecmdTraitSimWorker() {
        return recmdTraitSimWorker;
    }

    public void setRecmdTraitSimWorker(List<SimWorkerEntity> recmdTraitSimWorker) {
        this.recmdTraitSimWorker = recmdTraitSimWorker;
    }

    public List<SimWorkerEntity> getRecmdInterestSimWorker() {
        return recmdInterestSimWorker;
    }

    public void setRecmdInterestSimWorker(List<SimWorkerEntity> recmdInterestSimWorker) {
        this.recmdInterestSimWorker = recmdInterestSimWorker;
    }

    public Integer getDoingMissionsNum() {
        return doingMissionsNum;
    }

    public Map<Integer, WorkerInfoEntity> getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(Map<Integer, WorkerInfoEntity> workerInfo) {
        this.workerInfo = workerInfo;
    }

    public void setDoingMissionsNum(Integer doingMissionsNum) {
        this.doingMissionsNum = doingMissionsNum;
    }

    public DoingMissionEntity[] getDoingMissionEntities() {
        return doingMissionEntities;
    }

    public void setDoingMissionEntities(DoingMissionEntity[] doingMissionEntities) {
        this.doingMissionEntities = doingMissionEntities;
    }

    public Integer getDoneMissionNum() {
        return doneMissionNum;
    }

    public void setDoneMissionNum(Integer doneMissionNum) {
        this.doneMissionNum = doneMissionNum;
    }

    public DoneMissionEntity[] getDoneMissionEntities() {
        return doneMissionEntities;
    }

    public void setDoneMissionEntities(DoneMissionEntity[] doneMissionEntities) {
        this.doneMissionEntities = doneMissionEntities;
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

    public Integer getTotalCheckNum() {
        return totalCheckNum;
    }

    public void setTotalCheckNum(Integer totalCheckNum) {
        this.totalCheckNum = totalCheckNum;
    }

    public Integer getRightCheckNum() {
        return rightCheckNum;
    }

    public List<RecmdMissionEntity> getLabel_recmdMissionEntityList() {
        return label_recmdMissionEntityList;
    }

    public void setLabel_recmdMissionEntityList(List<RecmdMissionEntity> label_recmdMissionEntityList) {
        this.label_recmdMissionEntityList = label_recmdMissionEntityList;
    }

    public List<RecmdMissionEntity> getTail_recmdMissionEntityList() {
        return tail_recmdMissionEntityList;
    }

    public void setTail_recmdMissionEntityList(List<RecmdMissionEntity> tail_recmdMissionEntityList) {
        this.tail_recmdMissionEntityList = tail_recmdMissionEntityList;
    }

    public List<RecmdMissionEntity> getUserInterest_recmdMissionEntityList() {
        return userInterest_recmdMissionEntityList;
    }

    public void setUserInterest_recmdMissionEntityList(List<RecmdMissionEntity> userInterest_recmdMissionEntityList) {
        this.userInterest_recmdMissionEntityList = userInterest_recmdMissionEntityList;
    }

    public List<RecmdMissionEntity> getUserTrait_recmdMissionEntityList() {
        return userTrait_recmdMissionEntityList;
    }

    public void setUserTrait_recmdMissionEntityList(List<RecmdMissionEntity> userTrait_recmdMissionEntityList) {
        this.userTrait_recmdMissionEntityList = userTrait_recmdMissionEntityList;
    }

    public List<Long> getUninterestedList() {
        return uninterestedList;
    }

    public void setUninterestedList(List<Long> uninterestedList) {
        this.uninterestedList = uninterestedList;
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

    public RecmdWeighEntity getRecmdWeighEntity() {
        return recmdWeighEntity;
    }

    public void setRecmdWeighEntity(RecmdWeighEntity recmdWeighEntity) {
        this.recmdWeighEntity = recmdWeighEntity;
    }

    @Override
    public String toString() {
        return "WorkerEntity{" +
                "workerId=" + workerId +
                ", rank=" + rank +
                ", points=" + points +
                ", doingMissionsNum=" + doingMissionsNum +
                ", doingMissionEntities=" + Arrays.toString(doingMissionEntities) +
                ", doneMissionNum=" + doneMissionNum +
                ", doneMissionEntities=" + Arrays.toString(doneMissionEntities) +
                ", label_recmdMissionEntityList=" + label_recmdMissionEntityList +
                ", tail_recmdMissionEntityList=" + tail_recmdMissionEntityList +
                ", userInterest_recmdMissionEntityList=" + userInterest_recmdMissionEntityList +
                ", userTrait_recmdMissionEntityList=" + userTrait_recmdMissionEntityList +
                ", uninterestedList=" + uninterestedList +
                ", recmdWeighEntity=" + recmdWeighEntity +
                ", totalAnswerNum=" + totalAnswerNum +
                ", rightAnswerNum=" + rightAnswerNum +
                ", wrongAnswerNum=" + wrongAnswerNum +
                ", toBeJudgeAnswerNum=" + toBeJudgeAnswerNum +
                ", answerAccuracy=" + answerAccuracy +
                ", workerInfo=" + workerInfo +
                ", totalCheckNum=" + totalCheckNum +
                ", rightCheckNum=" + rightCheckNum +
                ", wrongCheckNum=" + wrongCheckNum +
                ", toBeJudgeCheckNum=" + toBeJudgeCheckNum +
                ", checkAccuracy=" + checkAccuracy +
                ", recmdTraitSimWorker=" + recmdTraitSimWorker +
                ", recmdInterestSimWorker=" + recmdInterestSimWorker +
                ", recmdWeighEntity" + recmdWeighEntity +
                '}';
    }
}
