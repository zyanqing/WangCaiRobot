package dao;

import domain.OnlineRobot;

import java.util.List;

public interface OnlineRobotDao {

    public List<OnlineRobot> getRobots();

    public void updateRobot(OnlineRobot newRobot);

}











