<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/19
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城首页</title>
    <%--静态包含（引入外部文件）--%>
    <%@include file="/pages/common/head.jsp"%>


    <style>
        body{
            padding: 0px 0px 0px 0px;
            margin: auto;
        }
        a{

            text-decoration: none;
        }

        #div_0{
            width: 800px;
            height: 80px;
            margin: auto;
        }
        #div_1{
            float: left;

        }
        #div_2{
            margin-top: 40px;
            width: 500px;
            height: 60px;
            float: right;

        }

        #div_3{
            font-size: 20px;
            float: left;
        }
        #div_4{
            font-size: 20px;
            float: right;
        }

        #div_5{
            margin: auto;
        }
        table{

            margin: 20px auto;
            width: 1240px;
            height: 430px;
            /*background-color: blue;*/
        }

        span{
            font-size: 15px;
        }
        img{
            margin: auto;
            width: 230px;
            height: 250px;
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

    <script type="text/javascript">
        $(function () {
            //给加入购物车按钮绑定单击事件
            $("button.addToCart").click(function () {
                var bookId = $(this).attr("bookId");
                location.href = "<%=basePath%>cartServlet?action=addItem&id=" + bookId;

                //发送Ajax请求
                <%--$.getJSON("<%=basePath%>cartServlet","action=addItem&id=" + bookId,function (data) {--%>

                <%--});--%>
                // location.href = "http://www.baidu.com";
                <%--location.href = "${pageScope.basePath}cartServlet?action=addItem&id=" + bookId;--%>
                // location.href = "http://localhost:8080/My_book/cartServlet?action=addItem&id=" + bookId;
            });
        });
    </script>
</head>
<body>
    <div id="div_0">
        <div id="div_1">
            <h1>网上书城</h1>
        </div>
        <div id="div_2">
            <div id="div_3">
                <c:if test="${sessionScope.login == null}">
                    <a style="margin-left: 300px" href="pages/user/login.jsp">登录</a> |
                    <a href="pages/user/regist.jsp">注册</a>
                </c:if>
                <c:if test="${sessionScope.login != null}">
                    <a>欢迎"${sessionScope.login.username}"登录网上书城</a> |
<%--                    <c:if test="${sessionScope.login.role == 0}">--%>
                        <a href="orderServlet?action=pageOrderByUserId">我的订单 </a>|
<%--                    </c:if>--%>

                    <a href="userServlet?action=logout">注销</a>
                </c:if>
            </div>

            <div id="div_4">
                <c:if test="${sessionScope.login != null}">
<%--                    <c:if test="${sessionScope.login.role == 0}">--%>
                        <a href="cartServlet?action=page">购物车</a>
<%--                    </c:if>--%>
                </c:if>

            </div>
        </div>
    </div>

    <div id="div_5">

        <table>

            <tr>
                <c:forEach items="${requestScope.page.items}" var="book">
                    <td>
                        <div>
                            <img alt="" src="${book.imgPath}"  />
                            <br />
                            <span>书名：</span>
                            <span>${book.name}</span>
                            <br />
                            <span>作者：</span>
                            <span>${book.author}</span>
                            <br />
                            <span>价格：</span>
                            <span>￥${book.price}</span>
                            <br />
                            <span>书籍销量：</span>
                            <span>${book.sales}</span>
                            <br />
                            <c:if test="${sessionScope.login.username != null}">
                                <c:if test="${book.stock > 0}">
                                    <button bookId="${book.id}" class="addToCart">加入购物车</button>
                                </c:if>
                                <c:if test="${book.stock <= 0}">
                                    <button>库存不足</button>
                                </c:if>
                            </c:if>
                        </div>
                    </td>

                </c:forEach>


            </tr>

            <tr>
                <td colspan="4">
                    <%@include file="../common/nav_page.jsp"%>

                </td>

            </tr>
        </table>
    </div>



</body>
</html>
