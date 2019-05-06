package action;

import com.opensymphony.xwork2.ActionSupport;
import service.ChatRecordService;
import serviceImpl.ChatRecordServiceImpl;
import utils.ChatUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;

public class TempAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        System.out.println("我被执行了");
        return NONE;
    }

    public String run()  {

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

            //SequenceInputStream是一个串联流，能够把两个流结合起来，通过该对象就可以将
            //getInputStream方法和getErrorStream方法获取到的流一起进行查看了，当然也可以单独操作
            SequenceInputStream sis = new SequenceInputStream(pr.getInputStream(), pr.getErrorStream());
            InputStreamReader inst = new InputStreamReader(sis, "utf-8");//设置编码格式并转换为输入流
            BufferedReader br = new BufferedReader(inst);//输入流缓冲区

            String res = null;
            StringBuilder sb = new StringBuilder();
            while ((res = br.readLine()) != null) {//循环读取缓冲区中的数据
//            sb.append(res + "\n");
//            System.out.print(res + "\n");//输出获取的数据
             String result = ChatUtils.chat(res, "00000");

             chatRecordService.saveRecord(res, result, "测试");
            }

        }catch ( Exception e ){
            System.out.print(e.getMessage());
        }finally {
            return NONE;
        }

    }
}
