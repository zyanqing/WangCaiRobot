package dao;

import domain.Robot;

import java.util.List;

public interface OnlineRobotDao {

    public List<Robot> getRobots();

    public void updateRobot(Robot newRobot);



}











