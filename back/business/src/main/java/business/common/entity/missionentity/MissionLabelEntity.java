package business.common.entity.missionentity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Entity
@Table(name = "missionlabel")
public class MissionLabelEntity {


    @Id
    private Long missionLabelId;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> missionIdList;

    public MissionLabelEntity(Long missionLabelId,Set<Long> missionIdList) {
        this.missionLabelId=missionLabelId;
        this.missionIdList = missionIdList;
    }

    public MissionLabelEntity() {
        this.missionLabelId=new Long(-1);
        this.missionIdList = new HashSet<>();
    }

    public Long getMissionLabelId() {
        return missionLabelId;
    }

    public void setMissionLabelId(Long missionLabelId) {
        this.missionLabelId = missionLabelId;
    }

    public Set<Long> getMissionIdList() {
        return missionIdList;
    }

    public void setMissionIdList(Set<Long> missionIdList) {
        this.missionIdList = missionIdList;
    }


}
