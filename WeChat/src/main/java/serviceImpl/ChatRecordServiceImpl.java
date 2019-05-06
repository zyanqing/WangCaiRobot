package serviceImpl;

import dao.ChatRecordDao;
import daoImpl.ChatRecordDaoImpl;
import service.ChatRecordService;

public class ChatRecordServiceImpl implements ChatRecordService {

    ChatRecordDao ChatRecordDao = new ChatRecordDaoImpl();

    @Override
    public void saveRecord(String user_ms, String robot_ms, String userName) {
        ChatRecordDao.saveRecord(user_ms,robot_ms,userName);
    }

    @Override
    public Long deletChat(long chatRecord_id) {
       return ChatRecordDao.deletChat(chatRecord_id);
    }

    @Override
    public void cleanChat(long user_id) {

        ChatRecordDao.cleanChat(user_id);

    }

}
