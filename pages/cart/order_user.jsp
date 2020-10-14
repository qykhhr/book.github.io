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
    <title>我的订单</title>

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
            margin-top: 20px;
        }
        table{
            text-align: center;
            width: 1000px;
            height: 400px;
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
            我的订单
        </div>

        <div id="div2">
            <span><a href="clientBookServlet?action=page">返回书城 | </a></span>
            <span><a href="userServlet?action=logout">注销</a></span>
        </div>
    </div>
    <br />
    <div id="div3">
        <div id="div4">
            <table>
                <c:if test="${requestScope.countByUserId == 0}">
                    <tr>
                        <td colspan="4"><a href="index.jsp"><h1>亲，您还没有订单！快去浏览商品吧！</h1></a></td>
                    </tr>

                </c:if>

                <c:if test="${requestScope.countByUserId > 0}">
                    <tr>
                        <td>日期</td>
                        <td>金额</td>
                        <td>订单号</td>
                        <td>状态</td>
                    </tr>

                    <c:forEach items="${requestScope.page.items}" var="order">
                        <tr>
                            <td>${order.createTime}</td>
                            <td>${requestScope.sumPrice}</td>
                            <td>${order.orderId}</td>
                            <td>
                                <c:if test="${order.status == 0}">
                                    未发货
                                </c:if>

                                <c:if test="${order.status == 1}">
                                    <a href="orderServlet?action=signOrder&orderId=${order.orderId}">已发货（点击签收）</a>
                                </c:if>

                                <c:if test="${order.status == 2}">
                                    已签收
                                </c:if>

                            </td>

                            <td><a href="orderServlet?action=showOrderItem&orderId=${order.orderId}">查看详情</a> </td>
                        </tr>
                    </c:forEach>


                    <tr>
                        <td colspan="7">
                            <%@include file="../common/nav_page.jsp"%>

                        </td>

                    </tr>
                </c:if>

            </table>
        </div>
    </div>
</div>

</body>
</html>
