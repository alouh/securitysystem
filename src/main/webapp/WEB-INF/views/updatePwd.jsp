<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/23 0023
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <title>修改密码</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/jeryCzc.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/updatePwd.js"></script>
    <script src="<%=basePath%>resources/plug/cookie.js"></script>
    <script src="<%=basePath%>resources/plug/jsencrypt.js"></script>
    <script src="<%=basePath%>resources/plug/md5.js"></script>
    <script src="<%=basePath%>resources/plug/jeryCzc.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
</head>
<body style="overflow-y:auto;">

<div class="panel panel-default">
    <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="oldPassword" class="col-sm-3 control-label">原始密码：</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="oldPassword" placeholder="请输入原始密码"/>
                </div>
                <h4 style="color: red;">*</h4>
            </div>
            <div class="form-group">
                <label for="newPassword" class="col-sm-3 control-label">新密码：</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="newPassword" placeholder="请输入新密码"/>
                </div>
             <h4 style="color: red;">*</h4>
            </div>
            <div class="form-group">
                <label for="newPasswordAgain" class="col-sm-3 control-label">确认新密码：</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="newPasswordAgain" placeholder="请输入新密码"/>
                </div>
              <h4 style="color: red;">*</h4>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="returnIndex()">返回</button>
        <button type="button" class="btn btn-primary" onclick="updatePwd()">确定</button>
    </div>
</div>



<!-- 信息提示modal -->
<div class="modal fade" id="msg">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="msgText"></p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" onclick="returnIndex()">确定</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="jeryCzc.jsp" %>

</body>
</html>
