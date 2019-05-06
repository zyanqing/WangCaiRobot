package service;

public interface ChatRecordService {

    public void saveRecord(String user_ms, String robot_ms, String userName);

    public Long deletChat(long chatRecord_id);

    public void cleanChat(long user_id);

}
