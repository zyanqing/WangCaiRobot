package dao;

import domain.Robot;

public interface OnlineRobotDao {

    public Robot updateRobot(Robot oldRobot);

    public void saveOnlineRobot(Robot robot);

    public void deletOnlineRobot(Robot robot);

    public void changeRobot(Robot robot);

}











