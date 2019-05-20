package daoImpl;

import dao.OnlineRobotDao;
import domain.OnlineRobot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class OnlineRobotDaoImpl implements OnlineRobotDao {


    @Override
    public OnlineRobot updateRobot(OnlineRobot oldRobot) {

        OnlineRobot newRobot = null;

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from OnlineRobot r where r.robot_user_name=?0");
        query.setParameter(0, oldRobot.getRobot_user_name());

        List<OnlineRobot> list = query.list();

        if (list.size() > 0) {
            newRobot = (OnlineRobot) list.get(0);
        }

        transaction.commit();
        return newRobot != null ? newRobot : oldRobot;
    }

    @Override
    public void saveOnlineRobot(OnlineRobot robot) {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(robot);

        transaction.commit();

    }

    @Override
    public void deletOnlineRobot(OnlineRobot robot) {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from OnlineRobot r where r.robot_user_name=?0");
        query.setParameter(0, robot.getRobot_user_name());

        List<OnlineRobot> list = query.list();

        if (list.size() > 0) {
            session.delete (list.get(0));
        }

        transaction.commit();

    }
}





















