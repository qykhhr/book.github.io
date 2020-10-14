<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/18
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>

    <style>
        form{
            /*background-color:red;*/
            width: 400px;
            height: 400px;
            margin: auto;
        }

        .login_1{
            width: 300px;
            height: 40px;
            margin: auto;
        }

        #submit{
            margin: auto;
            width: 300px;
            height: 40px;
        }

        #errorMsg{
            color: red;
            margin: 100px auto 0;
            /*float: right;*/
            width: 300px;
            height: 40px;
        }
    </style>
    <%@include file="/pages/common/head.jsp"%>
    <script>
        $(function () {
            $("#submit").click(function () {
                var username = $("#username").val();
                var patt = /^\w{5,12}$/;
                if(!patt.test(username)){
                    $("#errorMsg").text("用户名不合法");
                    return false;
                }
                $("#errorMsg").text("");
            });

        });

    </script>
</head>
<body>
    <div>
        <h1 style="text-align: center;margin-top: 30px;">登录用户</h1>
        <div id="errorMsg">
<%--            <%=request.getAttribute("msg")==null? "请输入用户名和密码" : request.getAttribute("msg") %>--%>
            ${empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}
        </div>
        <form action="userServlet?action=login" method="post" >
            <div class="login_1">
                用户名称：<input type="text" id="username"
                            name="username"
<%--                    <%=request.getAttribute("username")==null? "" : request.getAttribute("username") %>--%>
                            value="${requestScope.username}"
                            placeholder="请输入用户名" />
            </div>

            <div class="login_1">
                用户密码：<input type="password" id="password"
                            name="password"
                            placeholder="请输入密码"/>
            </div>

            <div class="login_1">
                <input type="submit" id="submit" value="登录"/>
            </div>
        </form>
    </div>
</body>
</html>
