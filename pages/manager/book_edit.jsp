<%--
  Created by IntelliJ IDEA.
  User: 雨林
  Date: 2020/9/20
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改/添加图书信息</title>
    <style>
        #div1{
            text-align: center;
            margin: auto;
            width: 1000px;
            height: 100px;
        }
        #div3{
            border: 1px solid;
            margin: auto;
            width: 1200px;
            height: 450px;
        }


        table{
            text-align: center;
            width: 1000px;
            height: 200px;
            margin: auto;
            /*border-collapse: collapse;*/
        }
    </style>

    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

    <div id="div1"><h1>修改/添加图书信息</h1></div>
    <div id="div3">
        <form action="manager/bookServlet" method="post">
            <input type="hidden" name="pageNo" value="${param.pageNo}"/>
            <input type="hidden" name="action" value="${empty param.id ? "add" : "update"}"/>
            <input type="hidden" name="id" value="${requestScope.book.id}" />
            <table>
                <tr>
                    <td>书名</td>
                    <td>作者</td>
                    <td>价格</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>

                <tr>
                    <td><input type="text" name="name" value="${requestScope.book.name}"></td>
                    <td><input type="text" name="author" value="${requestScope.book.author}"></td>
                    <td><input type="text" name="price" value="${requestScope.book.price}"></td>
                    <td><input type="text" name="sales" value="${requestScope.book.sales}"></td>
                    <td><input type="text" name="stock" value="${requestScope.book.stock}"></td>
                    <td><input type="submit" value="提交"/></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
