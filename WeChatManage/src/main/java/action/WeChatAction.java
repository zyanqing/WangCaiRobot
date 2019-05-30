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
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class WeChatAction extends ActionSupport implements ModelDriven<OnlineRobot> {

    private OnlineRobot onlineRobot = new OnlineRobot();

    @Override
    public OnlineRobot getModel() {
        return onlineRobot;
    }

    public void login() {

//        String basePath = "/Users/anjubao/Desktop/Maven/robots/";
        String basePath = "/tmp/robots/";

        String dirPath = basePath + System.currentTimeMillis();

        File file = new File(dirPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }

        String filePath = dirPath + "/WeChat-1.0.jar";

        File srcFile = new File(basePath + "WeChat-1.0.jar");
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Timer timer = new Timer();


        try {
            Runtime rt = Runtime.getRuntime();
            final Process pr = rt.exec("java -jar " + filePath);

            timer.schedule(new TimerTask() {
                public void run() {
                    pr.destroy();
                }
            }, 30 * 1000);

            InputStreamReader in = new InputStreamReader(pr.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String str = null;

            while ((str = br.readLine()) != null) {
                System.out.println(str);
                if (str.contains("登录成功")) {
                    timer.cancel();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String robot1() {
        login();
        return NONE;
    }

    public String robot2() {
        login();
        return NONE;
    }

    public String robot3() {
        login();
        return NONE;
    }

    public String robot4() {
        login();
        return NONE;
    }

    public String robot5() {
        login();
        return NONE;
    }

    public String robot6() {
        login();
        return NONE;
    }

    public String robot7() {
        login();
        return NONE;
    }

    public String robot8() {
        login();
        return NONE;
    }

    public String robot9() {
        login();
        return NONE;
    }

    public String robot10() {
        login();
        return NONE;
    }

    public String updateCfg() {

        ActionContext context = ActionContext.getContext();

        Map<String, Object> map = context.getParameters();

        String[] values = (String[]) map.get("email");

        RobotConfiguration configuration = new RobotConfiguration();
        configuration.setDefault_email_address(Arrays.toString(values).replace("[","").replace("]",""));
        RobotConfigurationDao dao = new RobotConfigurationDaoImpl();
        dao.updateRobotConfiguration(configuration);

        HttpServletResponse response = ServletActionContext.getResponse();

        response.setCharacterEncoding("utf-8");

        try {
            response.getWriter().write("{\"success\":\"ok\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }

    public String getCfg() {

        RobotConfigurationDao dao = new RobotConfigurationDaoImpl();
        RobotConfiguration configuration = dao.getRobotConfiguration();

        String jsonCfg = JSON.toJSONString(configuration);

        HttpServletResponse response = ServletActionContext.getResponse();

        response.setCharacterEncoding("utf-8");

        try {
            response.getWriter().write(jsonCfg);
        } catch (IOException e) {
            e.printStackTrace();
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


    public String updateRobot() {

        OnlineRobotDao dao = new OnlineRobotDaoImpl();
        dao.updateRobot(onlineRobot);

        HttpServletResponse response = ServletActionContext.getResponse();

        response.setCharacterEncoding("utf-8");

        try {
            response.getWriter().write("{\"success\":\"ok\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }


}

















