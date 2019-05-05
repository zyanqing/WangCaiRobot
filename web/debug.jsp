<%--
  Created by IntelliJ IDEA.
  User: anjubao
  Date: 2019/4/28
  Time: 下午6:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="${pageContext.request.contextPath}/manageCaht_talk" method="post">

    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户ID：<input type="number" name="username"/><br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;消息：<input type="text" name="message"/><br/>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交"/><br/>


    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    url  :  119.23.41.164:8080/robot/manageCaht_talk <br/>
    必填参数：<br/>
    username  :  只能为数字,用于数据库中用户的有效标识 <br/>
    message  :  要发送给图灵的文字对话

</form>

</body>
</html>
