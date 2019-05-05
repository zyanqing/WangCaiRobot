package action;

import com.opensymphony.xwork2.ActionSupport;
import wechat4j.Wechat;

public class WeChatAction extends ActionSupport {

    public String autoLogin(){

        // 实例化微信客户端
        Wechat wechat = new Wechat();
        // 自动登录
        wechat.autoLogin();

        return "autoLogin";
    }

}
