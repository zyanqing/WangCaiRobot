package domain;

public class ChatRecord {

    private User user;
    private Long chatRecord_id;
    private String robot_record;
    private String user_record;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getChatRecord_id() {
        return chatRecord_id;
    }

    public void setChatRecord_id(Long chatRecord_id) {
        this.chatRecord_id = chatRecord_id;
    }

    public String getRobot_record() {
        return robot_record;
    }

    public void setRobot_record(String robot_record) {
        this.robot_record = robot_record;
    }

    public String getUser_record() {
        return user_record;
    }

    public void setUser_record(String user_record) {
        this.user_record = user_record;
    }
}
