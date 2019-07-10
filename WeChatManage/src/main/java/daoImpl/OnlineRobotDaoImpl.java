package daoImpl;

import dao.OnlineRobotDao;
import domain.Robot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class OnlineRobotDaoImpl implements OnlineRobotDao {

    @Override
    public List<Robot> getRobots() {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Robot");

        List<Robot> list = query.list();

        transaction.commit();

        return list;
    }

    @Override
    public void updateRobot(Robot newRobot) {

        Robot oldRobot = null;

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from OnlineRobot");

        List<Robot> list = query.list();

        if (list.size() > 0){

            oldRobot = list.get(0);
            oldRobot.setStatus(newRobot.getStatus());
            oldRobot.setIsMute(newRobot.getIsMute());
            oldRobot.setIsPicture(newRobot.getIsPicture());
            oldRobot.setIsTtext(newRobot.getIsTtext());
            oldRobot.setIsVideo(newRobot.getIsVideo());
            oldRobot.setIsVoice(newRobot.getIsVoice());

            session.update(oldRobot);
        }

        transaction.commit();

    }

}





















