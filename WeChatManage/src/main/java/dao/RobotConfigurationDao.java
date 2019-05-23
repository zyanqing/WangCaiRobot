package dao;

import domain.RobotConfiguration;

public interface RobotConfigurationDao {

    public RobotConfiguration getRobotConfiguration();

    public RobotConfiguration updateRobotConfiguration(RobotConfiguration configuration);

}
