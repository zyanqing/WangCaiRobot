package utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import domain.Robot;
import domain.TuringAccount;
import utils.encrypt.EncryptUtils;

public class ChatUtils {


    private static final String url = "http://openapi.tuling123.com/openapi/api/v2"; //接口地址
    private static final String key = "59191ca89153451c86fb5f632deb8d18"; //apikey
    private static final String secret = "eb1a59a6e1444286"; //密钥

    public static String sendTextMsg(String message, String userid, Robot robot) throws Exception {

        TuringAccount turingAccount = robot.getTuringAccount();

        //请求内容
        String data = "{\"perception\":{\"inputText\":{\"text\":\"" + message + "\"}},\"reqType\":0,\"userInfo\":{\"key\":\"" + key + "\",\"userId\":\"" + userid + "\"}}";
        String timestamp = String.valueOf(System.currentTimeMillis());
        //加密内容
        data = EncryptUtils.encrypt(data, turingAccount.getKey(), turingAccount.getSecret(), timestamp);
        //请求参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("key", turingAccount.getKey());
        jsonParam.put("timestamp", timestamp);
        jsonParam.put("data", data);

        JSONObject jsonObject = JSONObject.parseObject(HttpUtils.post(url, jsonParam.toString()));

        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject obj1 = jsonArray.getJSONObject(0);
        JSONObject obj2 = obj1.getJSONObject("values");

        return obj2.getString("text");
    }

    public static String sendImgMsg(String imageUrl, String userid, Robot robot) throws Exception {

        TuringAccount turingAccount = robot.getTuringAccount();

        //请求内容
        String data = "{\"perception\":{\"inputImage\":{\"url\":\"" + imageUrl + "\"}},\"reqType\":1,\"userInfo\":{\"key\":\"" + key + "\",\"userId\":\"" + userid + "\"}}";
        String timestamp = String.valueOf(System.currentTimeMillis());
        //加密内容
        data = EncryptUtils.encrypt(data,turingAccount.getKey(), turingAccount.getSecret(), timestamp);
        //请求参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("key", turingAccount.getKey());
        jsonParam.put("timestamp", timestamp);
        jsonParam.put("data", data);

        JSONObject jsonObject = JSONObject.parseObject(HttpUtils.post(url, jsonParam.toString()));

        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject obj1 = jsonArray.getJSONObject(0);
        JSONObject obj2 = obj1.getJSONObject("values");

        return obj2.getString("text");
    }


    public static String sendVoiceMsg(String voiceUrl, String userid, Robot robot) throws Exception {

        TuringAccount turingAccount = robot.getTuringAccount();

        //请求内容
        String data = "{\"perception\":{\"inputMedia\":{\"url\":\"" + voiceUrl + "\"}},\"reqType\":2,\"userInfo\":{\"key\":\"" + key + "\",\"userId\":\"" + userid + "\"}}";
        String timestamp = String.valueOf(System.currentTimeMillis());
        //加密内容
        data = EncryptUtils.encrypt(data,turingAccount.getKey(), turingAccount.getSecret(), timestamp);
        //请求参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("key", turingAccount.getKey());
        jsonParam.put("timestamp", timestamp);
        jsonParam.put("data", data);

        JSONObject jsonObject = JSONObject.parseObject(HttpUtils.post(url, jsonParam.toString()));

        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject obj1 = jsonArray.getJSONObject(0);
        JSONObject obj2 = obj1.getJSONObject("values");

        return obj2.getString("text");
    }


//    @Test
//    public void test() throws Exception {
//
//
//      String text = sendVoiceMsg("http://wangcairobot.com/voice/1.mp3", "123456789");
//
//      System.out.println(text);
//
//    }

}
