package domain;

public class RobotConfiguration {

    private Long id;

    private String notifyMail = "13824452827@139.com";

    private String notifyPhone = "13824452827";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotifyMail() {
        return notifyMail;
    }

    public void setNotifyMail(String notifyMail) {
        this.notifyMail = notifyMail;
    }

    public String getNotifyPhone() {
        return notifyPhone;
    }

    public void setNotifyPhone(String notifyPhone) {
        this.notifyPhone = notifyPhone;
    }

    @Override
    public String toString() {
        return "RobotConfiguration{" +
                "id=" + id +
                ", notifyMail='" + notifyMail + '\'' +
                ", notifyPhone='" + notifyPhone + '\'' +
                '}';
    }
}
