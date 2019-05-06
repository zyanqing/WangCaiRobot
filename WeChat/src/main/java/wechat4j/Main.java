package wechat4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.ChatRecordDao;
import daoImpl.ChatRecordDaoImpl;
import utils.ChatUtils;
import wechat4j.handler.ReceivedMsgHandler;
import wechat4j.model.ReceivedMsg;
import wechat4j.model.UserInfo;

import java.io.PrintWriter;
import java.util.Map;

public class Main {

    public static void main(String[] strs) {

//         实例化微信客户端
        Wechat wechat = new Wechat();
        final PrintWriter pw = new PrintWriter(System.out);
        wechat.addReceivedMsgHandler(new ReceivedMsgHandler() {
            @Override
            public void handleAllType(Wechat wechat, ReceivedMsg msg) {

                if (msg.getMsgType().intValue() == 1) {

                    try {
                        UserInfo contact = wechat.getContactByUserName(false, msg.getFromUserName());
                        String result = ChatUtils.chat(msg.getContent(), "123456789");
                        Map maps = (Map<String, String>) JSON.parse(result);
                        JSONArray jsonArray = (JSONArray) maps.get("results");
                        Map results = (Map<String, JSONObject>) jsonArray.get(0);
                        Map values = (Map<String, String>) results.get("values");
                        String text = (String) values.get("text");

                        if (text != null && !text.equals("")) {
                            wechat.sendTextToUserName(msg.getFromUserName(), text);
                            ChatRecordDao chatRecordService = new ChatRecordDaoImpl();
                            chatRecordService.saveRecord(msg.getContent(), text, contact.getNickName());
                        }
                    } catch (Exception e) {
                        pw.println(e.getMessage());
                        pw.flush();
                    }


                }

            }
        });

        // 自动登录
        wechat.autoLogin();

    }

}
