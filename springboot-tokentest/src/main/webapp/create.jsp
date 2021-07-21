<%--
  Created by IntelliJ IDEA.
  User: wshh
  Date: 2018/9/3
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建token</title>
</head>
<body>
    生成token:<p>${msg}</p>
    <a href="/check">检查token</a>
    <%
        String M = session.getAttribute("dongserver").toString();
        session.setAttribute("dongclient",M);
    %>
</body>
</html>
