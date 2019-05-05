package utils;

import net.sf.json.JSONObject;
import utils.encrypt.EncryptUtils;

public class ChatUtils {


    private static final String url = "http://openapi.tuling123.com/openapi/api/v2"; //接口地址
    private static final String key = "59191ca89153451c86fb5f632deb8d18"; //apikey
    private static final String secret = "eb1a59a6e1444286"; //密钥

    public static String chat(String message,String userid) throws Exception {

        //请求内容
        String data = "{\"perception\":{\"inputText\":{\"text\":\"" + message+ "\"}},\"reqType\":0,\"userInfo\":{\"key\":\""+key+"\",\"userId\":\""+userid+"\"}}";
        String timestamp = String.valueOf(System.currentTimeMillis());
        //加密内容
        data = EncryptUtils.encrypt(data, key, secret, timestamp);
        //请求参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("key", key);
        jsonParam.put("timestamp", timestamp);
        jsonParam.put("data", data);

        return HttpUtils.post(url, jsonParam.toString());
    }

}
