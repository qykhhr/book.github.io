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

    <script>
        $(function () {
           $("a.delBtn").click(function () {
                return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text()+"】吗？");
           });

            $("a.delAll").click(function () {
                return confirm("你确定要清空购物车吗？");
            });

            $(".cartCount").change(function () {
                var id = $(this).attr("cartItemId");
                var name = $(this).parent().parent().find("td:first").text();
                var count = this.value;
                if(confirm("您确定要修改【" + name +  "】数量为："+ count +"吗？")){
                    location.href = "<%=basePath%>cartServlet?action=update&id=" + id + "&count=" + count;
                }else{
                    this.value = this.defaultValue;
                }

            });
        });
    </script>
</head>
<body>
<div>
    <div id="div_0">
        <div id="div1">
            购物车
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

                <c:if test="${empty requestScope.page.items}">
                    <tr>
                        <td colspan="5"><h1><a href="index.jsp">亲，当前购物车为空哦，浏览商品吧！！！</a></h1></td>
                    </tr>
                </c:if>

                <c:if test="${not empty requestScope.page.items}">
                    <tr>
                        <td>商品名称</td>
                        <td>数量</td>
                        <td>单价</td>
                        <td>金额</td>
                        <td>操作</td>
                    </tr>

                    <c:forEach items="${requestScope.page.items}" var="entry">
                        <tr>
                            <td>${entry.name}</td>
                            <td><input type="text" cartItemId="${entry.id}" class="cartCount" style="width: 60px;text-align: center" value="${entry.count}"/></td>
                            <td>${entry.price}</td>
                            <td>${entry.totalPrice}</td>
                            <td><a class="delBtn" href="cartServlet?action=delete&id=${entry.id}">删除</a></td>
                        </tr>
                    </c:forEach>


                    <tr>
                        <td colspan="2">购物车中共有 ${requestScope.sumCount} 件产品</td>
                        <td>总金额 ${requestScope.sumPrice} 元</td>
                        <td><a class="delAll" href="cartServlet?action=clear">清空购物车</a></td>
                        <td><a href="orderServlet?action=createOrder&userId=${sessionScope.login.id}">去结账</a></td>
                    </tr>

                    <tr>
                        <td colspan="5">
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
