package action;

import com.opensymphony.xwork2.ActionSupport;

public class TempAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
         System.out.println("我被执行了");
        return NONE;
    }

    public String run(){
        System.out.println("我被执行了");
        return SUCCESS;
    }
}
