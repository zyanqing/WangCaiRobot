package wechat4j;

import utils.MailUtils;
import wechat4j.enums.ExitType;
import wechat4j.handler.ExitEventHandler;

public class ExitEventHandlerImpl implements ExitEventHandler {

    /**
     * 针对所有类型的退出事件
     *
     * @param wechat 微信客户端
     * @param type   退出类型
     * @param t      异常
     */
    @Override
    public void handleAllType(Wechat wechat, ExitType type, Throwable t) {

    }

    /**
     * 针对错误导致的退出事件
     *
     * @param wechat 微信客户端
     */
    @Override
    public void handleErrorExitEvent(Wechat wechat) {
        MailUtils.sendMail(wechat.getRobotConfiguration().getDefault_email_address(),"微信退出通知", "错误导致的退出事件", wechat.getOnlineRobot().getRobot_nick_name());

    }

    /**
     * 针对远程人为导致的退出事件
     *
     * @param wechat 微信客户端
     */
    @Override
    public void handleRemoteExitEvent(Wechat wechat) {
        MailUtils.sendMail(wechat.getRobotConfiguration().getDefault_email_address(),"微信退出通知", "远程人为导致的退出事件", wechat.getOnlineRobot().getRobot_nick_name());

    }

    /**
     * 针对本地任务导致的退出事件
     *
     * @param wechat 微信客户端
     */
    @Override
    public void handleLocalExitEvent(Wechat wechat) {
        MailUtils.sendMail(wechat.getRobotConfiguration().getDefault_email_address(),"微信退出通知", "本地任务导致的退出事件", wechat.getOnlineRobot().getRobot_nick_name());

    }

}
