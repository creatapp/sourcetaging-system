package business.common.entity.missionentity;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Entity
@Table(name = "mission")
public class MissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionId;

    private Long requesterId;

    private String title;

    private double difficulty;

    private Integer taskTime;

    private Integer traitDiffer=1;

    private double recommendRefer=0.0;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Integer> label;

    private String description;

    private String type;//标注类型

    private Integer totalPoints;


    /*
    * 实现任务分配的逻辑存储安排
    *
    * 为解决上限问题，就应该在每次进行分配的时候主动给工人分配不大于一定数量（如10）
    * 的标注或检查的tag【此时要分配好tagId】，每次要求分配的时候需要避免一个人既标又检查同一张图片的情况
    *
    * 分配的时候可能分不整齐（因为有些人的标注需要跳过），如果用户需要完成10张图片的标注，后来只有9张了，
    * 直接判断用户的标注可以直接提交的。但是这种情况的话暂时不用考虑
    * */

    //流程一：待分配的队列，一旦建立任务，就应该在该队列里建立所有pictureId
    @Lob()
    private Blob planToTagQueue_blob;//pictureId

    //流程二：一旦产生分配，就将pictureId从队列里移除，并放到Set中，
    // 并且由逻辑层为pictureId分配tagId放到map中,这个map也作为最终结果的存放位置（由picId得到tag）

    @ElementCollection(fetch = FetchType.LAZY)//使用此注解配置集合映射关联
    private Set<Long> assignedTagSet;//pictureId

    @ElementCollection
    @JoinTable(name = "mission_pic2tagmap",joinColumns = @JoinColumn(name = "missionid"))
    @MapKeyColumn(name = "pictureid")//对应键值列名称
    private Map<Long,Long> pic2TagMap;//pictureId:tagId

    //流程三：可以分配检查的任务队列，只有当工人完成该批次任务之后才能一次性地放到这里
    //实现方式可以先来先服务，也可以循环整个队列
    @Lob()
    private Blob planToCheckQueue_blob;//pictureId

    //流程四：一旦分配完成（一个picture被分给足额的不同的人），就以移除到这个map中
    //仅当工人一次性提交足额检查的时候才会将对应Integer位置+1
    @ElementCollection
    @JoinTable(name = "mission_assignedcheckmap",joinColumns = @JoinColumn(name = "checktimes"))
    @MapKeyColumn(name = "pictureid")//对应键值列名称
    private Map<Long,Integer> assignedCheckMap;//pictureId:0..n

    //流程五：当工人提交检查的时候,检查该tagId对应的tagDomain是否有足额的检查数量
    //是就进行正误判断，从该map中移除，错误的话清除pic2TagMap的tagId，并将该pictureId移到planToTagQueue中
    //最后别忘了进行结算（亏本生意也要做）

    //额外流程：如何处理过期的标注？
    //对所有tagDomain进行扫描显然不是一件好的方案
    //所以应该有一个接口，使得mission在分配完以后告诉统计模块，而统计模块有一张表用于自动维护和回收这些标注
    //这个接口是统计模块思考的一部分，我尽量早一点做好这方面的接口
    //但是这边完全可以先定义一个·analysisHelper·之类的，先给出自己需要的接口


    //计数，用于判断任务状况
    private Integer pictureTotalNum;

    private Integer pictureLeftNum;

    private Integer pictureFinishedNum;//完全完成


    private String startDate;

    private String endDate;
    //其它
    private Integer tagPrice;

    private Integer checkPrice;

    private Long[] participants;

    public MissionEntity(Long requesterId, Set<Integer> label, String title, String description,
                         String type, Integer totalPoints, Queue<Long> planToTagQueue,double difficulty,
                         Set<Long> assignedTagSet,Map<Long, Long> pic2TagMap,
                         Queue<Long> planToCheckQueue, Map<Long, Integer> assignedCheckMap,
                         Integer pictureTotalNum, Integer pictureLeftNum, Integer pictureFinishedNum,
                         Integer tagPrice, Integer checkPrice, Long[] participants) {
        this.requesterId = requesterId;
        this.title = title;
        this.difficulty=difficulty;
        this.description = description;
        this.label=label;
        this.type = type;
        this.totalPoints = totalPoints;
        this.setPlanToTagQueue(planToTagQueue);
        this.assignedTagSet = assignedTagSet;
        this.pic2TagMap = pic2TagMap;
        this.setPlanToCheckQueue(planToCheckQueue);
        this.assignedCheckMap = assignedCheckMap;
        this.pictureTotalNum = pictureTotalNum;
        this.pictureLeftNum = pictureLeftNum;
        this.pictureFinishedNum = pictureFinishedNum;
        this.tagPrice = tagPrice;
        this.checkPrice = checkPrice;
        this.participants = participants;
        this.setStartDate();
    }

    public MissionEntity(){
        this.traitDiffer=1;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    private void setStartDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.startDate = sdf.format(d);
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public Set<Integer> getLabel() {
        return label;
    }

    public void setLabel(Set<Integer> label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Blob getPlanToCheckQueue_blob() {
        return planToCheckQueue_blob;
    }

    public void setPlanToCheckQueue_blob(Blob planToCheckQueue_blob) {
        this.planToCheckQueue_blob = planToCheckQueue_blob;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Queue<Long> getPlanToTagQueue() {
        try {
            Object obj = null;
            ObjectInputStream in = new ObjectInputStream(
                    this.planToTagQueue_blob.getBinaryStream());
            obj = in.readObject();
            in.close();
            return (Queue<Long>) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Integer taskTime) {
        this.taskTime = taskTime;
    }

    public Integer getTraitDiffer() {
        return traitDiffer;
    }

    public void setTraitDiffer(Integer traitDiffer) {
        this.traitDiffer = traitDiffer;
    }

    public double getrecommendRefer() {
        return recommendRefer;
    }

    public void setrecommendRefer(double recommendRefer) {
        this.recommendRefer = recommendRefer;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Blob getPlanToTagQueue_blob() {
        return planToTagQueue_blob;
    }

    public void setPlanToTagQueue_blob(Blob planToTagQueue_blob) {
        this.planToTagQueue_blob = planToTagQueue_blob;
    }

    public void setPlanToTagQueue(Queue<Long> planToTagQueue) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(planToTagQueue);
            byte[] bytes = out.toByteArray();
            outputStream.close();
            this.planToTagQueue_blob=new SerialBlob(bytes);

        } catch (Exception e) {
        }

    }

    public Set<Long> getAssignedTagSet() {
        return assignedTagSet;
    }

    public void setAssignedTagSet(Set<Long> assignedTagSet) {
        this.assignedTagSet = assignedTagSet;
    }

    public Map<Long, Long> getPic2TagMap() {
        return pic2TagMap;
    }

    public void setPic2TagMap(Map<Long, Long> pic2TagMap) {
        this.pic2TagMap = pic2TagMap;
    }

    public Queue<Long> getPlanToCheckQueue() {
        try {
            Object obj = null;
            ObjectInputStream in = new ObjectInputStream(this.planToCheckQueue_blob.getBinaryStream());
            obj = in.readObject();
            in.close();
            return (Queue<Long>) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public void setPlanToCheckQueue(Queue<Long> planToCheckQueue) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(planToCheckQueue);
            byte[] bytes = out.toByteArray();
            outputStream.close();
            this.planToCheckQueue_blob=new SerialBlob(bytes);
        } catch (Exception e) {
        }
    }

    public Map<Long, Integer> getAssignedCheckMap() {
        return assignedCheckMap;
    }

    public void setAssignedCheckMap(Map<Long, Integer> assignedCheckMap) {
        this.assignedCheckMap = assignedCheckMap;
    }

    public Integer getPictureTotalNum() {
        return pictureTotalNum;
    }

    public void setPictureTotalNum(Integer pictureTotalNum) {
        this.pictureTotalNum = pictureTotalNum;
    }

    public Integer getPictureLeftNum() {
        return pictureLeftNum;
    }

    public void setPictureLeftNum(Integer pictureLeftNum) {
        this.pictureLeftNum = pictureLeftNum;
    }

    public Integer getPictureFinishedNum() {
        return pictureFinishedNum;
    }

    public void setPictureFinishedNum(Integer pictureFinishedNum) {
        this.pictureFinishedNum = pictureFinishedNum;
    }

    public Integer getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(Integer tagPrice) {
        this.tagPrice = tagPrice;
    }

    public Integer getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(Integer checkPrice) {
        this.checkPrice = checkPrice;
    }

    public Long[] getParticipants() {
        return participants;
    }

    public void setParticipants(Long[] participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "MissionEntity{" +
                "missionId=" + missionId +
                ", requesterId=" + requesterId +
                ", title='" + title + '\'' +
                ", difficulty=" + difficulty +
                ", taskTime=" + taskTime +
                ", traitDiffer=" + traitDiffer +
                ", recommendRefer=" + recommendRefer +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", totalPoints=" + totalPoints +
                ", planToTagQueue_blob=" + planToTagQueue_blob +
                ", assignedTagSet=" + assignedTagSet +
                ", pic2TagMap=" + pic2TagMap +
                ", planToCheckQueue_blob=" + planToCheckQueue_blob +
                ", assignedCheckMap=" + assignedCheckMap +
                ", pictureTotalNum=" + pictureTotalNum +
                ", pictureLeftNum=" + pictureLeftNum +
                ", pictureFinishedNum=" + pictureFinishedNum +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", tagPrice=" + tagPrice +
                ", checkPrice=" + checkPrice +
                ", participants=" + Arrays.toString(participants) +
                '}';
    }

}
