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
    <title>注册</title>

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

        #sub_btn{
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

            /*给验证码的图片绑定单击事件*/
            $("#code_img").click(function () {
                //在事件响应的function函数中有一个this对象，这个this对象，是当前正在响应事件的DOM对象
                //src属性表示图片的路径，它可读可写

                this.src="<%=basePath%>kaptcha.jpg?d=" + new Date();
            });


            $("#sub_btn").click(function () {
                var username = $("#username").val();
                var password_1 = $("#password_1").val();
                var password_2 = $("#password_2").val();
                var email = $("#email").val();
                var code = $("#code").val();

                //使用正则表达式验证用户名以及。。。。。。
                //创建正则表达式对象
                var pattUsername = /^\w{5,12}$/;
                if( !pattUsername.test(username) ){
                    $("#errorMsg").text("用户名不合法");
                    return false;
                }

                if(password_1 != password_2){
                    $("#errorMsg").text("密码不一致");
                    return false;
                }
                var pattPassword = /^\w{5,12}$/;
                if(!pattPassword.test(password_1)){
                    $("#errorMsg").text("密码不合法");
                    return false;
                }

                var pattEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if(!pattEmail.test(email)){
                    $("#errorMsg").text("邮箱不合法");
                    return false;
                }

                code = $.trim(code);
                if(code.length == 0 || code == null || code == ""){
                    $("#errorMsg").text("验证码不能为空");
                    return false;
                }
                $("#errorMsg").text("");
            });

        });
    </script>
</head>
<body>
    <div>
        <h1 style="text-align: center;margin-top: 30px;">注册用户</h1>
        <div id="errorMsg">
<%--            <%=request.getAttribute("msg")==null? "请输入用户名和密码" : request.getAttribute("msg")%>--%>
            ${empty requestScope.msg ? "" : requestScope.msg}
        </div>
        <form action="userServlet?action=regist" method="post" >
            <div class="login_1">
                用户名称：<input type="text" id="username"
                            name="username" placeholder="请输入用户名"
<%--                            value="<%=request.getAttribute("username")==null?"" : request.getAttribute("username")%>"--%>
                            value="${requestScope.username}"
                             />
            </div>

            <div class="login_1">
                用户密码：<input type="password" id="password_1" name="password" placeholder="请输入密码"/>
            </div>

            <div class="login_1">
                确认密码：<input type="password" id="password_2" placeholder="请输入密码"/>
            </div>

            <div class="login_1">
                用户邮箱：<input type="text" id="email" name="email"
<%--                            value="<%=request.getAttribute("email")==null?"" : request.getAttribute("email")%>"--%>
                            value="${requestScope.email}"
                            placeholder="请输入邮箱"/>
            </div>

            <div class="login_1">
                验证码：&nbsp;&nbsp;&nbsp;<input type="text" id="code" name="code"
                             style="width: 60px"
                             value="${requestScope.code}"
                             placeholder="请输入验证码"/>
                        <img id="code_img" style="width: 100px; height: 28px" src="<%=basePath%>kaptcha.jpg"/>
            </div>

            <div class="login_1">
                <input type="submit" id="sub_btn" value="注册"/>
            </div>
        </form>
    </div>
</body>
</html>
