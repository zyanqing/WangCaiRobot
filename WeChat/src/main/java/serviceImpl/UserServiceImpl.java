package serviceImpl;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import domain.ChatRecord;
import domain.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao UserDao = new UserDaoImpl();

    @Override
    public User find(String userName) {
        return UserDao.find(userName);
    }

    @Override
    public User register(String userName, String password) {
        return UserDao.register(userName,password);
    }

    @Override
    public List<User> queryUser() {
        return UserDao.queryUser();
    }

    @Override
    public List<ChatRecord> queryChatRecord(long user_id) {
        return UserDao.queryChatRecord(user_id);
    }

    @Override
    public void deletUser(long user_id) {

        UserDao.deletUser(user_id);
    }


}
