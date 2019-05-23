package daoImpl;

import dao.RobotConfigurationDao;
import domain.RobotConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class RobotConfigurationDaoImpl implements RobotConfigurationDao {
    @Override
    public RobotConfiguration getRobotConfiguration() {

        RobotConfiguration robotConfiguration = null;


        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from RobotConfiguration");
        List<RobotConfiguration> list = query.list();

        if (list.size() > 0){
            robotConfiguration = list.get(0);
        }else {
            robotConfiguration = new RobotConfiguration();
            session.save(robotConfiguration);
        }

        transaction.commit();
        return robotConfiguration;
    }

    @Override
    public RobotConfiguration updateRobotConfiguration(RobotConfiguration configuration) {

        RobotConfiguration newConfiguration = null;

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from RobotConfiguration");
        List<RobotConfiguration> list = query.list();

        if (list.size() > 0){
            newConfiguration = list.get(0);
            newConfiguration.setDefault_email_address(configuration.getDefault_email_address());
        }else {
            newConfiguration = new RobotConfiguration();
            newConfiguration.setDefault_email_address(configuration.getDefault_email_address());
        }
        session.saveOrUpdate(newConfiguration);
        transaction.commit();
        return newConfiguration;
    }

}
