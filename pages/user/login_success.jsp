<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/18
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <%@include file="/pages/common/head.jsp"%>
    <style>
        div{
            width: 200px;
            height: 200px;
            margin: 200px auto;
            font-size: 20px;

        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div>
        <h3>登录成功</h3>
        <a href="clientBookServlet?action=page">&nbsp;返回主页</a>
    </div>

</body>
</html>
