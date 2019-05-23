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
    public List<OnlineRobot> getRobots() {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from OnlineRobot");

        List<OnlineRobot> list = query.list();

        transaction.commit();

        return list;
    }

    @Override
    public void updateRobot(OnlineRobot newRobot) {

        OnlineRobot oldRobot = null;

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from OnlineRobot");

        List<OnlineRobot> list = query.list();

        if (list.size() > 0){

            oldRobot = list.get(0);
            oldRobot.setRobot_is_mute(newRobot.getRobot_is_mute());
            oldRobot.setRobot_is_reply_picture(newRobot.getRobot_is_reply_picture());
            oldRobot.setRobot_is_reply_text(newRobot.getRobot_is_reply_text());
            oldRobot.setRobot_is_reply_video(newRobot.getRobot_is_reply_video());
            oldRobot.setRobot_is_reply_voice(newRobot.getRobot_is_reply_voice());

            session.update(oldRobot);
        }

        transaction.commit();

    }

}





















