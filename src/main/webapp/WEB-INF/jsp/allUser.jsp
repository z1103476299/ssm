<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/15
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <h3><a href="/user/toAdd">增加</a></h3>
        <h4>欢迎您,${sessionScope.User}</h4>
        <tr>
            <td>用户名</td>
            <td>密码</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <c:forEach var="us" items="${list}">
            <tr>
                <td>${us.username}</td>
                <td>${us.password}</td>
                <td><a href="/user/getUser?id=${us.id}">编辑</a></td>
                <td><a href="/user/delUser?id=${us.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <a href="/user/goLogin" style="text-decoration: none">返回登录</a>
</body>
</html>
