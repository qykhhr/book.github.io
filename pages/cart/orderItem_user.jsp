<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/20
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>订单详情</title>

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
            width: 350px;
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
            margin-top: 20px;
        }
        table{
            text-align: center;
            width: 1000px;
            height: 200px;
            margin: auto;
            border-collapse: collapse;
        }

        #page_nav{
            width: 700px;
            height: 30px;
            margin: 0 auto;
            font-size: 15px;
        }

        #pn_input{
            width: 40px;
        }

    </style>

</head>
<body>
<div>
    <div id="div_0">
        <div id="div1">
            订单详情
        </div>

        <div id="div2">
            <c:if test="${empty loginManagerUsername}">
                <span><a href="orderServlet?action=pageOrderByUserId">我的订单 | </a></span>
            </c:if>
            <c:if test="${not empty loginManagerUsername}">
                <span><a href="orderServlet?action=pageOrder">订单管理 | </a></span>
            </c:if>
            <span><a href="clientBookServlet?action=page">返回书城</a></span>
        </div>
    </div>
    <br />
    <div id="div3">
        <div id="div4">
            <table>

                    <tr>
                        <td>商品名称</td>
                        <td>数量</td>
                        <td>单价</td>
                        <td>金额</td>
                        <td>订单号</td>
                    </tr>

                    <tr>
                        <td>${requestScope.orderItem.name}</td>
                        <td>${requestScope.orderItem.count}</td>
                        <td>${requestScope.orderItem.price}</td>
                        <td>${requestScope.orderItem.totalPrice}</td>
                        <td>${requestScope.orderItem.orderId}</td>
                    </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
