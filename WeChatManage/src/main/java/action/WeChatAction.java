package action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.OnlineRobotDao;
import dao.RobotConfigurationDao;
import daoImpl.OnlineRobotDaoImpl;
import daoImpl.RobotConfigurationDaoImpl;
import domain.Robot;
import domain.RobotConfiguration;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class WeChatAction extends ActionSupport implements ModelDriven<Robot> {

    private Robot onlineRobot = new Robot();

    @Override
    public Robot getModel() {
        return onlineRobot;
    }

    public String login() {

//        String basePath = "/Users/ajb/Desktop/Maven/robot/WeChat.jar";
//        String basePath = "/tmp/robots/";

//        String dirPath = basePath + System.currentTimeMillis();
//
//        File file = new File(dirPath);
//        if (!file.exists() && !file.isDirectory()) {
//            file.mkdir();
//        }
//
//        String filePath = dirPath + "/WeChat-1.0.jar";
//
//        File srcFile = new File(basePath + "WeChat-1.0.jar");
//        File destFile = new File(filePath);
//        try {
//            FileUtils.copyFile(srcFile, destFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Timer timer = new Timer();
        String qrPath = null;

        try {
//            Runtime rt = Runtime.getRuntime();
//            final Process pr = rt.exec("java -jar " + "/Users/ajb/Desktop/Maven/WangCaiRobot/WeChat/target/WeChat-1.0.jar");

            List<String> params = new ArrayList<String>();
            params.add("java");
            params.add("-jar");
            params.add("/Users/ajb/Desktop/Maven/WangCaiRobot/WeChat/target/WeChat-1.0.jar");
            ProcessBuilder processBuilder = new ProcessBuilder(params);
            final Process pr = processBuilder.start();

//             定时
            timer.schedule(new TimerTask() {
                public void run() {
                    pr.destroy();
                }
            }, 150 * 1000);

            InputStreamReader in = new InputStreamReader(pr.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String str = null;

            while ((str = br.readLine()) != null) {
                System.out.println(str);
                if (str.contains("qrPath:")) {
                    timer.cancel();
                    qrPath = str.replace("qrPath:","");

                    Map<String,Object> map = new HashMap<String,Object>();

                    Map<String,Object> dataMap = new HashMap<>();
                    dataMap.put("path",qrPath);

                    map.put("result","200");
                    map.put("message","请扫码登录");
                    map.put("data",dataMap);

                    String jsonCfg = JSON.toJSONString(map);

                    HttpServletResponse response = ServletActionContext.getResponse();
                    response.setCharacterEncoding("utf-8");
                    ServletActionContext.getResponse().getWriter().write(jsonCfg);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("========== Action结束 ==========");
        return NONE;
    }


    public String updateCfg() {

        ActionContext context = ActionContext.getContext();

        Map<String, Object> map = context.getParameters();

        String[] values = (String[]) map.get("email");

        RobotConfiguration configuration = new RobotConfiguration();
        configuration.setNotifyMail(Arrays.toString(values).replace("[", "").replace("]", ""));
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
        List<Robot> robots = dao.getRobots();

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

















