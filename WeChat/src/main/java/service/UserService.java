package service;

import domain.ChatRecord;
import domain.User;

import java.util.List;

public interface UserService {
    public User find(String userName);

    public User register(String userName, String password);

    public List<User> queryUser();

    public List<ChatRecord> queryChatRecord(long user_id);

    public void deletUser(long user_id);

}
