package action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class WeChatAction extends ActionSupport {

    public String login() {

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("java -jar /Users/anjubao/Desktop/Maven/robot/WeChat.jar");

            InputStreamReader in = new InputStreamReader(pr.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String str = null;

            while ((str = br.readLine()) != null){
                 System.out.println(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return NONE;
    }

}
