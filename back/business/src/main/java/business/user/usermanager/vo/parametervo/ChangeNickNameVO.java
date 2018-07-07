package business.user.usermanager.vo.parametervo;

public class ChangeNickNameVO {

    private Long id;

    private String newNickName;

    public ChangeNickNameVO(Long id, String newNickName) {
        this.id = id;
        this.newNickName = newNickName;
    }

    public ChangeNickNameVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewNickName() {
        return newNickName;
    }

    public void setNewNickName(String newNickName) {
        this.newNickName = newNickName;
    }

    @Override
    public String toString() {
        return "ChangeNickNameVO{" +
                "id='" + id + '\'' +
                ", newNickName='" + newNickName + '\'' +
                '}';
    }
}
