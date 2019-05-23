package action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.OnlineRobotDao;
import dao.RobotConfigurationDao;
import daoImpl.OnlineRobotDaoImpl;
import daoImpl.RobotConfigurationDaoImpl;
import domain.OnlineRobot;
import domain.RobotConfiguration;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


public class WeChatAction extends ActionSupport implements ModelDriven<OnlineRobot> {

    private OnlineRobot onlineRobot = new OnlineRobot();

    @Override
    public OnlineRobot getModel() {
        return onlineRobot;
    }

    public String login() {

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("java -jar /Users/anjubao/Desktop/Maven/robot/WeChat.jar");

            InputStreamReader in = new InputStreamReader(pr.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String str = null;

            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return NONE;
    }

    public String updateCfg() {

        ActionContext context = ActionContext.getContext();

        Map<String, Object> map = context.getParameters();

        String email = null;

        if ((email = (String) map.get("email")) != null) {
            RobotConfiguration configuration = new RobotConfiguration();
            configuration.setDefault_email_address(email);
            RobotConfigurationDao dao = new RobotConfigurationDaoImpl();
            dao.updateRobotConfiguration(configuration);
        }

        return NONE;
    }


    public String getRobots() {

        OnlineRobotDao dao = new OnlineRobotDaoImpl();
        List<OnlineRobot> robots = dao.getRobots();

        String jsonRobots = JSON.toJSONString(robots);

        HttpServletResponse response = ServletActionContext.getResponse();

        response.setCharacterEncoding("utf-8");

        try {
            response.getWriter().write(jsonRobots);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }


    public String updateRobot(){

        OnlineRobotDao dao = new OnlineRobotDaoImpl();
        dao.updateRobot(onlineRobot);

        return NONE;
    }


}

















