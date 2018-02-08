<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-27
  Time: 下午 4:08
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
    <title>安全审计维护</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/jeryCzc.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/jeryCzc.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/IllegalDevice.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">外接非法USB设备终端</h3>
    </div>
    <div class="panel-head" id="toolbar">
        <form role="form" class="form-inline">
            <div class="form-group" style="width:280px; margin-left: 30px">
                <label class="col-sm-3 control-label" for="id_Ip">网络地址：</label>
                <div class="col-sm-8">
                    <input class="form-control" id="id_Ip" type="text"/>
                </div>
            </div>
            <input id="btn-search" type="button" class="btn btn-primary" onclick="findRules()" value="查询">
        </form>
    </div>
    <table id="tb_illegalDevice" class="table table-striped"></table>
</div>

<%@ include file="jeryCzc.jsp" %>
</body>
</html>
