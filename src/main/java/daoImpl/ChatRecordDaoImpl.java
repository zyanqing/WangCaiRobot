package daoImpl;

import dao.ChatRecordDao;
import domain.ChatRecord;
import domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import serviceImpl.UserServiceImpl;
import utils.HibernateUtils;

import java.util.HashSet;

public class ChatRecordDaoImpl implements ChatRecordDao {

    service.UserService UserService = new UserServiceImpl();

    @Override
    public void saveRecord(String user_ms, String robot_ms, String userName) {

        User user = UserService.find(userName);

        if (user == null) {
            service.UserService userService = new UserServiceImpl();
            user = userService.register(userName, "123456");
        }

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        ChatRecord cr = new ChatRecord();

        user.setChatRecords(new HashSet<ChatRecord>());

        cr.setUser(user);
        cr.setUser_record(user_ms);
        cr.setRobot_record(robot_ms);
        user.getChatRecords().add(cr);

        session.saveOrUpdate(user);

        transaction.commit();

    }

    @Override
    public Long deletChat(long chatRecord_id) {

        Long temp_id = null;

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        ChatRecord chatRecord = session.get(ChatRecord.class, chatRecord_id);

        if (chatRecord != null) {
            User user = chatRecord.getUser();
            temp_id = user.getUser_id();
            session.delete(chatRecord);
        }
        transaction.commit();

        return temp_id;
    }

    @Override
    public void cleanChat(long user_id) {

        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 查询1号客户
        User customer = session.get(User.class, user_id);// 发送一条查询客户的SQL
//        System.out.println(customer.getUserName());
        // 查看1号客户的每个联系人的信息
        for (ChatRecord c : customer.getChatRecords()) {// 发送一条根据客户ID查询联系人的SQL
            session.delete(c);
        }
        tx.commit();

    }
}
