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
import service.ChatRecordService;
import service.UserService;
import serviceImpl.ChatRecordServiceImpl;
import serviceImpl.UserServiceImpl;
import utils.HibernateUtils;
import utils.HttpUtils;

import java.io.*;


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


    public static void main(String[] ar) {


        try {
            System.out.println("我被执行了");
            ChatRecordService chatRecordService = new ChatRecordServiceImpl();

            Runtime rt = Runtime.getRuntime();//获得Runtime对象

            //exec方法第一个参数是执行的命令，第二个参数是环境变量，第三个参数是工作目录
            Process pr = rt.exec("java -jar /Users/anjubao/Desktop/Maven/WangCaiRobot/target/classes/WeChat.jar", null, null);

            //获取输出流并转换成缓冲区
//        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream(),"utf-8"));
//        bout.write("1 2");//输出数据
//        bout.close();//关闭流
            PrintWriter pw = new PrintWriter(System.out);
            //SequenceInputStream是一个串联流，能够把两个流结合起来，通过该对象就可以将
            //getInputStream方法和getErrorStream方法获取到的流一起进行查看了，当然也可以单独操作
            SequenceInputStream sis = new SequenceInputStream(pr.getInputStream(), pr.getErrorStream());
            InputStreamReader inst = new InputStreamReader(sis, "utf-8");//设置编码格式并转换为输入流
            BufferedReader br = new BufferedReader(inst);//输入流缓冲区

            String res = null;
            StringBuilder sb = new StringBuilder();
            while ((res = br.readLine()) != null) {//循环读取缓冲区中的数据
//            sb.append(res + "\n");

                if (res.contains("MsgText:")){
                    System.out.print(res + "\n");//输出获取的数据
                    pw.println("消息已收到" + "\n");
                    pw.flush();
                }else {
                    System.out.print(res + "\n");//输出获取的数据
                }

            }

        }catch ( Exception e ){
            System.out.print(e.getMessage());
        }

    }


}
