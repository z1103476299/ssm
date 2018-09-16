<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/15
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑页面</title>
</head>
<body>
    <form action="/user/update" method="post">
        <input type="hidden" value="${user.id}" name="id">
       姓名: <input type="text" value="${user.username}" name="username">
       密码: <input type="text" value="${user.password}" name="password">
        <input type="submit" value="编辑">
    </form>
</body>
</html>
