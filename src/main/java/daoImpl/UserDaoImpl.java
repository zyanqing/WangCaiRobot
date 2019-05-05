package daoImpl;

import dao.UserDao;
import domain.ChatRecord;
import domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Override
    public User find(String userName) {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = null;
        Query query = session.createQuery("from User u where u.userName=?0");
        query.setParameter(0, userName);
        list = query.list();

        if (list.size() > 0) {
            transaction.commit();
            return list.get(0);
        }
        transaction.commit();
        return null;
    }

    @Override
    public User register(String userName, String password) {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(password);

        session.save(user);

        transaction.commit();

        return find(userName);
    }

    @Override
    public List<User> queryUser() {

        List<User> list = null;

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User");
        list = query.list();

        transaction.commit();
        return list;
    }

    @Override
    public List<ChatRecord> queryChatRecord(long user_id) {

        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from User c inner join fetch c.chatRecords where c.user_id=?0");
        query.setParameter(0, user_id);
        List<User> list = query.list();
        User user = list.get(0);
        // 查询1号客户
//        User customer = session.get(User.class, user_id);// 发送一条查询客户的SQL
////        System.out.println(customer.getUserName());
//        // 查看1号客户的每个联系人的信息
//        for (ChatRecord linkMan : customer.getChatRecords()) {// 发送一条根据客户ID查询联系人的SQL
////            System.out.println(linkMan.getRobot_record());
//        }
        tx.commit();

        List<ChatRecord> chatRecords = new ArrayList<ChatRecord>();

        for (ChatRecord c : user.getChatRecords()) {
            chatRecords.add(c);
        }


        Comparator<ChatRecord> comparator = new Comparator<ChatRecord>(){
            public int compare(ChatRecord s1, ChatRecord s2) {
                //先排年龄
                    return s1.getChatRecord_id().intValue()-s2.getChatRecord_id().intValue();
            }
        };

        Collections.sort(chatRecords,comparator);

        return chatRecords;
    }

    @Override
    public void deletUser(long user_id) {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, user_id);
        session.delete(user);

        transaction.commit();
    }

}
