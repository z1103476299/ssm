<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/15
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加用户界面</title>
</head>
<body>

    <form action="/user/addUser" method="post">
        用户名:<input type="text" name="username">
        密码:<input type="password" name="password">
        <input type="submit" value="保存">
    </form>
</body>
</html>
