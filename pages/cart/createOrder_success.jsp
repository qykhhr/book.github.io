<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/20
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>支付成功</title>
    <style>
        #div_0{
            margin: auto;
            width: 1000px;
            height: 80px;
        }
        #div1{
            margin-top: 20px;
            width: 230px;
            height: 50px;
            float: left;
            font-size: 35px;
            /*margin: 20px auto;*/
        }

        a{
            text-decoration: none;
        }
        #div2{
            margin-top: 20px;
            font-size: 25px;
            float: right;
            width: 300px;
            height: 50px;
        }

        #div3{
            border: 1px solid;
            margin: auto;
            width: 1200px;
            height: 450px;
            /*background-color: red;*/
        }

        #div4{
            text-align: center;
            margin-top: 80px;
            font-size: 30px;
        }
    </style>
</head>
<body>
<div>
    <div id="div_0">
        <div id="div1">
            订单创建成功
        </div>

        <div id="div2">

        </div>
    </div>
    <br />
    <div id="div3">
        <div id="div4">
            <a href="orderServlet?action=pageOrderByUserId">支付成功，点击查看我的订单</a>
        </div>
    </div>
</div>

</body>
</html>
