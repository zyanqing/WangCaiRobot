package wechat4j;

import dao.OnlineRobotDao;
import daoImpl.OnlineRobotDaoImpl;
import utils.MailUtils;
import wechat4j.enums.ExitType;
import wechat4j.handler.ExitEventHandler;

import java.util.Date;

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
        // 保存退出时间
        OnlineRobotDao onlineRobotDao = new OnlineRobotDaoImpl();
        wechat.getOnlineRobot().setDropoutTime(new Date());
        wechat.getOnlineRobot().setStatus(0l);
        onlineRobotDao.updateRobot(wechat.getOnlineRobot());
        MailUtils.sendMail(wechat.getRobotConfiguration().getNotifyMail(),"微信退出通知", "错误导致的退出事件", wechat.getOnlineRobot().getNickName());

    }

    /**
     * 针对远程人为导致的退出事件
     *
     * @param wechat 微信客户端
     */
    @Override
    public void handleRemoteExitEvent(Wechat wechat) {
        // 保存退出时间
        OnlineRobotDao onlineRobotDao = new OnlineRobotDaoImpl();
        wechat.getOnlineRobot().setDropoutTime(new Date());
        wechat.getOnlineRobot().setStatus(0l);
        onlineRobotDao.changeRobot(wechat.getOnlineRobot());
        MailUtils.sendMail(wechat.getRobotConfiguration().getNotifyMail(),"微信退出通知", "远程人为导致的退出事件", wechat.getOnlineRobot().getNickName());

    }

    /**
     * 针对本地任务导致的退出事件
     *
     * @param wechat 微信客户端
     */
    @Override
    public void handleLocalExitEvent(Wechat wechat) {
        // 保存退出时间
        OnlineRobotDao onlineRobotDao = new OnlineRobotDaoImpl();
        wechat.getOnlineRobot().setDropoutTime(new Date());
        wechat.getOnlineRobot().setStatus(0l);
        onlineRobotDao.updateRobot(wechat.getOnlineRobot());
        MailUtils.sendMail(wechat.getRobotConfiguration().getNotifyMail(),"微信退出通知", "本地任务导致的退出事件", wechat.getOnlineRobot().getNickName());

    }

}
