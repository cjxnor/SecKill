<%--
  Created by IntelliJ IDEA.
  User: cjx
  Date: 2017/9/25
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="common/tag.jsp"%>
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
            <div class="panel-body">
                <h2 class="text-danger">
                    <%-- 显示时间图标 --%>
                    <span class="glyphicon glyphicon-time"></span>
                    <%-- 显示倒计时 --%>
                    <span class="glyphicon" id="secKill-box"></span>
                </h2>
            </div>
        </div>
    </div>

    <%-- 使用 Bootstrap 的 modal 显示弹出层，用于获取用户手机号 userPhone --%>
    <div class="modal fade" id="userPhone-modal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <%-- 显示关闭按钮 --%>
                    <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                    <h3 class="modal-title"><span class="glyphicon glyphicon-phone"></span>输入手机号</h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="userPhone" id="userPhone"
                                   placeholder="请输入手机号" class="form-control"/>
                            <span id="userPhoneMessage" class="glyphicon"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <%--<button class="btn btn-default">关闭</button>--%>
                    <button id="userPhoneBtn" class="btn btn-success">确定</button>
                </div>
            </div>
        </div>
    </div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Jquery Cookie 操作插件 -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<!-- Jquery countdown 倒计时插件 -->
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<script src="<%= basePath%>resources/js/secKill.js"></script>
<script type="text/javascript">
    jQuery(function () {
        //使用 EL 表达式传入参数
       secKill.detail.init({
            skId : ${stock.skId},
            startTime : ${stock.startTime.time},    //毫秒时间
            endTime : ${stock.endTime.time}
       });
    });
</script>
</html>

