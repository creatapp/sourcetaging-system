package business.common.entity.pictureentity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "picture")
public class PictureEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pictureId;

        private Long missionId;

    @Lob()
    private String pictureBits;

    public PictureEntity(){}

    public PictureEntity(Long missionId, String pictureBits) {
        this.missionId = missionId;
        this.pictureBits = pictureBits;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getPictureBits() {
        return pictureBits;
    }

    public void setPictureBits(String pictureBits) {
        this.pictureBits = pictureBits;
    }

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "PictureEntity{" +
                "pictureId=" + pictureId +
                ", missionId='" + missionId + '\'' +
                ", pictureBits='" + pictureBits + '\'' +
                '}';
    }
}
