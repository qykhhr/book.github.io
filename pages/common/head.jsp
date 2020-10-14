<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/19
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme()//获取协议号
                        + "://"
                        + request.getServerName()//获取IP地址
                        + ":"
                        + request.getServerPort()//获取端口号
                        + request.getContextPath()//获取工程路径
                        + "/";
    pageContext.setAttribute("basePath",basePath);
%>

<base href="<%=basePath%>">
<script src="static/script/jquery-1.7.2.js"></script>