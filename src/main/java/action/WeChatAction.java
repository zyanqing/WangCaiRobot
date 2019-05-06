package action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;

public class WeChatAction extends ActionSupport {

    public String autoLogin() throws Exception{

        System.out.println("我被执行了");

        Runtime rt = Runtime.getRuntime();//获得Runtime对象

        Process pr = rt.exec("java -jar /Users/anjubao/Desktop/Maven/WangCaiRobot/target/classes/WeChat.jar", null, null);


        SequenceInputStream sis = new SequenceInputStream(pr.getInputStream(), pr.getErrorStream());
        InputStreamReader inst = new InputStreamReader(sis, "utf-8");//设置编码格式并转换为输入流
        BufferedReader br = new BufferedReader(inst);//输入流缓冲区
        String res = null;
        StringBuilder sb = new StringBuilder();
        while ((res = br.readLine()) != null) {//循环读取缓冲区中的数据
            System.out.print(res + "\n");//输出获取的数据
        }

        return NONE;

    }

}
