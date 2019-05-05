package action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import domain.ChatRecord;
import org.apache.struts2.ServletActionContext;
import service.ChatRecordService;
import service.UserService;
import serviceImpl.ChatRecordServiceImpl;
import serviceImpl.UserServiceImpl;
import utils.ChatUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ChatAction extends ActionSupport {

    private String message;
    private String username;

    private Long chatRecord_id;

    private Long user_id;

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setChatRecord_id(Long chatRecord_id) {
        this.chatRecord_id = chatRecord_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void talk() {

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (username == null) {
            out.println("请输入用户ID");
            out.flush();
            out.close();
            return;
        }

        String result = null;
        try {
            result = ChatUtils.chat(message, username);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("机器人开小差了！");
            out.flush();
            out.close();
        }

        Map maps = (Map<String, String>) JSON.parse(result);
        JSONArray jsonArray = (JSONArray) maps.get("results");
        Map results = (Map<String, JSONObject>) jsonArray.get(0);
        Map values = (Map<String, String>) results.get("values");
        String text = (String) values.get("text");

        out.println(text);
        out.flush();
        out.close();

        if (text != null && !text.equals("")) {
            ChatRecordService chatRecordService = new ChatRecordServiceImpl();
            chatRecordService.saveRecord(message, text, username);

        }

    }

    public String deletChat() {

        ChatRecordService service = new ChatRecordServiceImpl();

        Long user_id = service.deletChat(chatRecord_id.longValue());

        ValueStack vs = ActionContext.getContext().getValueStack();

        UserService userService = new UserServiceImpl();

        List<ChatRecord> list = userService.queryChatRecord(user_id);

        if (list != null) {
            vs.set("chatRecords", list);
        }

        return "deletChat";
    }

    public String cleanChat() {

        ChatRecordService service = new ChatRecordServiceImpl();

        Long user_id = service.deletChat(chatRecord_id.longValue());

        service.cleanChat(user_id.longValue());

        return "cleanChat";
    }

    public String demo01() {

        /*
         *
         * ValueStack是Struts2的数据中转站
         *
         * root  : 通常放置的是java中的Object对象，后去root的数据不需要加#
         *
         * context   ：放置的是常见的web开发对象的引用，比如：request、session、application、parameters、attr（里面也包含的对root的引用）
         *
         * 通常所说的操作值栈指的就是操作ValueStack中的root区域（直接操作request等web中的对象，就相当于是操作context）
         *
         * 值栈与ActionContext的关系：
         *       当请求过来的时候，执行过滤器中doFliter方法，在这个方法中创建ActionContext，在创建ActionContext过程中创建ValueStack对象，将ValueStack对象
         *     传递给ActionContext对象。所以可以通过ActionContext获取值栈对象
         *       ActionContext对象之所以能够访问servlet的API（访问是域对象的数据）。因为在其内部有值栈的引用
         *
         * */

        /*
         * 向值栈中存入数据：
         *
         *   一：在Action中提供属性的get方法的方式
         *       默认的情况下，会将Action对象压如到值栈中，相应的Action的属性也会在值栈中(一般不用，因为当Action属性过多时不好维护，而且这个方式不能保证你存储的时候就是在栈顶，当多个属性同名的时候默认取第一个)
         *           <s:property value="user.username">
         *
         *   二：使用ValueStack中本身的方法
         *       push：存入的数据会在栈顶，因为压入的属性有get方法，所以会在root中显示属性名
         *       set：以键值的形式，先创建一个map对象让后再把map压入栈，因为没有相应的get方法，所以不会在root中显示属性名
         *       <s:property value="username">
         *
         * */

        /*
         *
         * 获取ValueStack中的数据
         *
         *       一：获取一个对象的数据
         *           <s:property value="username">
         *
         *       二：获取集合中的数据（向root中存入一个集合）
         *           <s:property value="list[0].username">
         *
         *       三：获取Context中的数据
         *           <s:property value="#request.username">
         *           <s:property value="#session.username">
         *           <s:property value="#application.username">
         *           <s:property value="#attr.username">
         *
         *        四：el获取
         *           ${username}
         * */

        /*
         *
         *   #号的用法
         *       一：获取Context中的数据
         *
         *       二：构建map集合
         *
         *
         *   遍历：
         *       <s:iterator var="i" value="list"> //也可以不定义属性名直接获取 <s:property value="username"/>
         *           <s:property value="i"/>
         *       </s:iterator>
         *
         *
         * */

        HttpServletRequest request = ServletActionContext.getRequest();

        ValueStack vs1 = (ValueStack) request.getAttribute("struct.valueStack");

        ValueStack vs2 = ActionContext.getContext().getValueStack();

        return null;
    }

}






















