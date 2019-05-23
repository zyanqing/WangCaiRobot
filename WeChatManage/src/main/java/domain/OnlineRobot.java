package domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OnlineRobot {

    private Long id;

    private String robot_user_name;

    private String robot_nick_name;

    private String robot_mail_address = "13824452827@139.com";

    private Long robot_status  = new Long(1);

    private Long robot_is_reply_text  = new Long(1);

    private Long robot_is_reply_picture  = new Long(1);

    private Long robot_is_reply_video  = new Long(1);

    private Long robot_is_reply_voice = new Long(1);

    private Long robot_is_mute  = new Long(0);

}
