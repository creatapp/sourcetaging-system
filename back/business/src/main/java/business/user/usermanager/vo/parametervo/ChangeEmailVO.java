package business.user.usermanager.vo.parametervo;

public class ChangeEmailVO {

    private Long id;

    private String newEmail;

    public ChangeEmailVO(Long id, String newEmail) {
        this.id = id;
        this.newEmail = newEmail;
    }

    public ChangeEmailVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    @Override
    public String toString() {
        return "ChangeEmailVO{" +
                "id='" + id + '\'' +
                ", newEmail='" + newEmail + '\'' +
                '}';
    }
}
