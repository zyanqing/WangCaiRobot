package domain;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String userName;
    private String userPassword;
    private Long user_id;

    private Set<ChatRecord> chatRecords = new HashSet<ChatRecord>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Set<ChatRecord> getChatRecords() {
        return chatRecords;
    }

    public void setChatRecords(Set<ChatRecord> chatRecords) {
        this.chatRecords = chatRecords;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", user_id=" + user_id +
                ", chatRecords=" + chatRecords +
                '}';
    }
}
