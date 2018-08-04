<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
    <h4>List page</h4>

    <br/>

    Welcome: <shiro:principal/>

    <br/><br/>

    <shiro:hasRole name="admin">
        <a href="admin.jsp">Admin Page</a>
    </shiro:hasRole>
    <shiro:hasRole name="user">
        <a href="user.jsp">Admin Page</a>
    </shiro:hasRole>

    <br/><br/>

    <a href="user/logout">Logout</a>
</body>
</html>
