package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import domain.ChatRecord;
import domain.User;
import org.apache.struts2.ServletActionContext;
import service.UserService;
import serviceImpl.UserServiceImpl;

import java.util.List;

public class UserAction extends ActionSupport {

    private String user_name;

    private String user_password;

    private Long user_id;

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String login(){


        if (user_name.equals("") && user_password.equals("") ){

            ServletActionContext.getRequest().getSession().setAttribute("existUser", user_name);

            UserService userService = new UserServiceImpl();

            List<User> userList = userService.queryUser();

            ValueStack vs = ActionContext.getContext().getValueStack();

            ServletActionContext.getRequest().getSession().setAttribute("list",userList);

            vs.set("list",userList);

            return "loginSuccess";
        }else {
            this.addActionError("用户名或密码错误！");
            return LOGIN;
        }
    }

    public String signOut(){

        ServletActionContext.getRequest().getSession().setAttribute("existUser", null);
        return LOGIN;
    }

    public String chatList(){

        ServletActionContext.getRequest().setAttribute("user_name",user_name);

        UserService userService = new UserServiceImpl();

        List<ChatRecord> list = userService.queryChatRecord(user_id.longValue());

        ValueStack vs = ActionContext.getContext().getValueStack();

        vs.set("chatRecords",list);

        return "querySuccess";
    }

    public String  delet(){

        UserService userService = new UserServiceImpl();

        userService.deletUser(user_id);

        List<User> userList = userService.queryUser();

        ValueStack vs = ActionContext.getContext().getValueStack();

        ServletActionContext.getRequest().getSession().setAttribute("list",userList);

        vs.set("list",userList);

        return "delet";
    }

    public String refreshMenu(){

        UserService userService = new UserServiceImpl();

        List<User> userList = userService.queryUser();

        ValueStack vs = ActionContext.getContext().getValueStack();

        ServletActionContext.getRequest().getSession().setAttribute("list",userList);

        vs.set("list",userList);

        return "refreshMenu";
    }

}
