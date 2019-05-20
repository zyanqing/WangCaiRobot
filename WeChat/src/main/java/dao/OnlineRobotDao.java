package dao;

import domain.OnlineRobot;

public interface OnlineRobotDao {

    public OnlineRobot updateRobot(OnlineRobot oldRobot);

    public void saveOnlineRobot(OnlineRobot robot);

    public void deletOnlineRobot(OnlineRobot robot);

}











