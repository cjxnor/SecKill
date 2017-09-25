<%--
  Created by IntelliJ IDEA.
  User: cjx
  Date: 2017/9/25
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%-- Bootstrap 模板 --%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%-- 静态包含共同的文件（TODO 动态包含和静态包含的区别） --%>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-heading">
                <h1>${stock.name}</h1>
            </div>
        </div>
        <div class="pane-body"></div>
    </div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>

