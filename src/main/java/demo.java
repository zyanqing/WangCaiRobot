import dao.ChatRecordDao;
import dao.UserDao;
import daoImpl.ChatRecordDaoImpl;
import daoImpl.UserDaoImpl;
import domain.ChatRecord;
import domain.User;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import service.UserService;
import serviceImpl.UserServiceImpl;
import utils.HibernateUtils;
import utils.HttpUtils;
import wechat4j.Wechat;


public class demo {

    @Test
    public void demo01() {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUserName("张三");
        user.setUserPassword("123456");

        ChatRecord chatRecordOne = new ChatRecord();
        chatRecordOne.setRobot_record("hi");
        chatRecordOne.setUser_record("hi!");

        ChatRecord chatRecordTwo = new ChatRecord();
        chatRecordTwo.setRobot_record("hi2");
        chatRecordTwo.setUser_record("hi!2");

        ChatRecord chatRecordOne1 = new ChatRecord();
        chatRecordOne1.setRobot_record("hi");
        chatRecordOne1.setUser_record("hi!");

        ChatRecord chatRecordTwo2 = new ChatRecord();
        chatRecordTwo2.setRobot_record("hi2");
        chatRecordTwo2.setUser_record("hi!2");

        user.getChatRecords().add(chatRecordOne);
        user.getChatRecords().add(chatRecordTwo);
        user.getChatRecords().add(chatRecordOne1);
        user.getChatRecords().add(chatRecordTwo2);
        chatRecordOne.setUser(user);
        chatRecordTwo.setUser(user);
        chatRecordOne1.setUser(user);
        chatRecordTwo2.setUser(user);

        session.save(user);

        transaction.commit();

    }

    @Test
    public void demo2() throws Exception {

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("message", "6666666");
        jsonParam.put("username", "6666666");

        String resut = HttpUtils.post("http://localhost:8083/robot/manageCaht_talk", jsonParam.toString());

        System.out.println(resut);
    }

    @Test
    public void demo3() {

        UserService service = new UserServiceImpl();
        User user = service.find("张三");
        System.out.println(user.getChatRecords());

    }

    @Test
    public void demo4() {

        UserDao dao = new UserDaoImpl();
        System.out.println(dao.register("huanwu", "123456"));

    }

    @Test
    public void demo5() {

        int a = 11;

        UserDao dao = new UserDaoImpl();
        System.out.println(dao.queryChatRecord(a));

    }

    @Test
    public void demo6() {
        UserService service = new UserServiceImpl();
        System.out.println(service.queryUser());

    }

    @Test
    public void demo7() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 查询1号客户
        User customer = session.get(User.class, 11l);// 发送一条查询客户的SQL
        System.out.println(customer.getUserName());
        // 查看1号客户的每个联系人的信息
        for (ChatRecord linkMan : customer.getChatRecords()) {// 发送一条根据客户ID查询联系人的SQL
            System.out.println(linkMan.getRobot_record());
        }
        tx.commit();

    }


    @Test
    public void demo8() {

        ChatRecordDao dao = new ChatRecordDaoImpl();
        dao.deletChat(6l);
    }

    @Test
    public void demo9() {

        // 实例化微信客户端
        Wechat wechat = new Wechat();
        // 自动登录
        wechat.autoLogin();
    }

}
