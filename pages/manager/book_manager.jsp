<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/20
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>图书管理系统</title>

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
            margin: 0px auto;
            font-size: 15px;
        }
        #pn_input{
            width: 40px;
        }

        #addBook{
            font-size: 18px;
            float: right;
        }

    </style>

    <script>
        $(function () {
            $("a.deleteClass").click(function () {
                // alert($(this).parent().parent());
                return confirm("您确定要删除【" +  $(this).parent().parent().find("td:first").text()+"】吗？");
            });
        });
    </script>
</head>
<body>
    <div>
        <div id="div_0">
            <div id="div1">
                图书管理系统
            </div>

            <div id="div2">
                <span><a href="pages/manager/manager.jsp">后台管理</a> </span>|
                <span><a href="orderServlet?action=pageOrder">订单管理</a> </span>|
                <span><a href="userServlet?action=logout">注销</a></span> </span>
            </div>
        </div>
        <br />
        <div id="div3">
            <div id="div4">
                <table>
                    <tr>
                        <td>书名</td>
                        <td>作者</td>
                        <td>价格</td>
                        <td>销量</td>
                        <td>库存</td>
                        <td colspan="2">操作</td>
                    </tr>

                    <c:forEach items="${requestScope.page.items}" var="book">
                            <tr>
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.price}</td>
                                <td>${book.sales}</td>
                                <td>${book.stock}</td>
                                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a> </td>
                                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
                            </tr>
                    </c:forEach>

                    <tr>
                       <td colspan="7">
                           <div id="addBook">
                               <a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageNo}">添加图书</a>
                           </div>

                       </td>
                    </tr>

                    <tr>
                        <td colspan="7">
                            <%--分页条--%>
                            <%@include file="../common/nav_page.jsp"%>

                        </td>

                    </tr>

                </table>
            </div>
        </div>
    </div>

</body>
</html>
