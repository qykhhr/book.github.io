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
    <title>订单管理系统</title>

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
                订单管理系统
            </div>

            <div id="div2">
                <span><a href="pages/manager/manager.jsp">后台管理</a> </span>|
                <span><a href="manager/bookServlet?action=page">图书管理</a> </span>|
                <span><a href="userServlet?action=logout">注销</a></span> </span>
            </div>
        </div>
        <br />
        <div id="div3">
            <div id="div4">
                <table>
                    <tr>
                        <td>日期</td>
                        <td>金额</td>
                        <td>订单号</td>
                        <td>状态</td>
                    </tr>

                    <c:forEach items="${requestScope.items}" var="order">
                        <tr>
                            <td>${order.createTime}</td>
                            <td>${requestScope.sumPrice}</td>
                            <td>${order.orderId}</td>
                            <td>
                                <c:if test="${order.status == 0}">
                                    <a href="orderServlet?action=sendOrder&orderId=${order.orderId}" >点击发货 </a>
                                </c:if>

                                <c:if test="${order.status == 1}">
                                    已发货
                                </c:if>

                                <c:if test="${order.status == 2}">
                                    已签收
                                </c:if>
                            </td>
                            <td><a href="orderServlet?action=showOrderItem&orderId=${order.orderId}">查看详情</a></td>
                        </tr>

                    </c:forEach>


                    <tr>
                        <td colspan="7">
                            <%@include file="../common/nav_page.jsp"%>

                        </td>

                    </tr>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
