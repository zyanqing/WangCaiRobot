import wechat4j.Wechat;

public class main {

    public static void main(String[] strgs){

        // 实例化微信客户端
        Wechat wechat = new Wechat();
        // 自动登录
        wechat.autoLogin();

    }

}
