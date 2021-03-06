package wechat4j;

import utils.ChatUtils;
import wechat4j.handler.ReceivedMsgHandler;
import wechat4j.model.ReceivedMsg;
import wechat4j.model.UserInfo;
import wechat4j.util.PropertiesUtil;
import wechat4j.util.WebWeixinApiUtil;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] strs) {

//         实例化微信客户端
        Wechat wechat = new Wechat();

        wechat.addExitEventHandler(new ExitEventHandlerImpl());

        final PrintWriter pw = new PrintWriter(System.out);
        wechat.addReceivedMsgHandler(new ReceivedMsgHandler() {
            @Override
            public void handleAllType(Wechat wechat, ReceivedMsg msg) {

                if (wechat.getOnlineRobot().isMute()) {
                    return;
                }

                if (msg.getMsgType().intValue() == 1 && wechat.getOnlineRobot().isText()) {

//                    String str = msg.getContent().substring(msg.getContent().indexOf("@旺财"));
//                    String message = str.replace("@旺财","");

                    try {
                        UserInfo contact = wechat.getContactByUserName(false, msg.getFromUserName());

                        String result = ChatUtils.sendTextMsg(msg.getContent(), String.valueOf(wechat.getLoginUserNickName().hashCode()),wechat.getOnlineRobot());

                        wechat.sendTextToUserName(msg.getFromUserName(), result);

                    } catch (Exception e) {
                        pw.println(e.getMessage());
                        pw.flush();
                    }

                }

                // 图片消息
                if (msg.getMsgType().intValue() == 3 && wechat.getOnlineRobot().isPicture()) {

                    String folderPath = PropertiesUtil.getProperty("ImagePath") + wechat.getLoginUserNickName();

                    File file = new File(folderPath);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdir();
                    }
                    String filePath = folderPath + "/" + msg.getMsgId() + ".jpg";

                    WebWeixinApiUtil.getImgMsg(wechat.getHttpClient(), wechat.getUrlVersion(), msg.getMsgId(), wechat.getSkey(), filePath);

                    String result = "";

                    String urlPath = "http://wangcairobot.com/img/" + wechat.getLoginUserNickName() + "/" + msg.getMsgId() + ".jpg";

                    try {
                        result = ChatUtils.sendImgMsg(urlPath, String.valueOf(wechat.getLoginUserNickName().hashCode()),wechat.getOnlineRobot());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    wechat.sendTextToUserName(msg.getFromUserName(), result);
                }

                // 语音消息
                if (msg.getMsgType().intValue() == 34 && wechat.getOnlineRobot().isVideo()) {

                    String folderPath = PropertiesUtil.getProperty("VoicePath") + wechat.getLoginUserNickName();

                    File file = new File(folderPath);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdir();
                    }
                    String filePath = folderPath + "/" + msg.getMsgId() + ".mp3";

                    WebWeixinApiUtil.getVoiceMsg(wechat.getHttpClient(), wechat.getUrlVersion(), msg.getMsgId(), wechat.getSkey(), filePath);

                    String result = "";

                    String urlPath = "http://wangcairobot.com/voice/" + wechat.getLoginUserNickName() + "/" + msg.getMsgId() + ".mp3";

                    try {
                        result = ChatUtils.sendVoiceMsg(urlPath, String.valueOf(wechat.getLoginUserNickName().hashCode()),wechat.getOnlineRobot());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    wechat.sendTextToUserName(msg.getFromUserName(), result);
                }

            }
        });

        // 自动登录
        wechat.autoLogin();

    }


//    @Test
//    public void demo() {
//        OnlineRobotDao onlineRobotDao = new OnlineRobotDaoImpl();
//        Robot robot = new Robot();
//        robot.setId(6l);
//        onlineRobotDao.changeRobot(robot);
//    }

}
