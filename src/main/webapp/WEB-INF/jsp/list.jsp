<%--
  Created by IntelliJ IDEA.
  User: cjx
  Date: 2017/9/25
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- isELIgnored 为 true 会把 EL 表达式当字符串处理 --%>
<%@ page isELIgnored="false" %>
<%-- 引入 jstl --%>
<%@include file="common/tag.jsp"%>
<%-- Bootstrap 模板 --%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%-- 静态包含共同的文件（TODO 动态包含和静态包含的区别） --%>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <%-- 页面显示部分 --%>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>秒杀列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>库存</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>创建时间</th>
                        <th>详情页</th>
                    </tr>
                    </thead>
                    <%-- TODO fmt 使用 pattern="" 时，页面显示的时间有问题，多了 12 分钟，或者和数据库无关 --%>
                    <tbody>
                        <c:forEach items="${stockList}" var="stockList" varStatus="stockStatus">
                            <tr>
                                <td>${stockList.name}</td>
                                <td>${stockList.number}</td>
                                <td>
                                    <%-- value 中传入的是一个 java Date 对象 --%>
                                    <fmt:formatDate value="${stockList.startTime}" type="both"/>
                                    <%--<fmt:formatDate value="${stockList.startTime}" pattern="yyyy-MM-dd HH:hh:mm"/>--%>
                                </td>
                                <td>
                                    <fmt:formatDate value="${stockList.endTime}" type="both"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${stockList.createTime}" type="both"/>
                                </td>
                                <td>
                                    <a class="btn btn-info" href="/seckill/${stockList.skId}/detail" target="_blank">详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>

