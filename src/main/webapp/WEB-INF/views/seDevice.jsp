<%@ page import="com.youotech.securitysystem.service.SeDeviceService" %>
<%@ page import="com.youotech.securitysystem.service.impl.SeDeviceServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.youotech.securitysystem.bo.SeDevice" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-23
  Time: 下午 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
    <title>白名单管理</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/jeryCzc.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/jeryCzc.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/seDevice.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">白名单管理</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
            <div class="form-group">
                <div class="col-sm-8">
                    <input class="form-control" id="sdIp" type="hidden"/>
                </div>
            </div>
            <div class="form-group" style="width:400px">
                <label class="col-sm-3 control-label" for="sdType_add">USB设备类型:</label>
                <div class="col-sm-8">
                    <select id="sdType_add" name="sdType_add" class="selectpicker show-tick form-control">
                        <option value="便携设备" selected="selected">便携设备</option>
                        <option value="无线WiFi">无线WiFi</option>
                        <option value="大容量存储">大容量存储</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-8">
                    <input class="form-control" id="sdUser" type="hidden"/>
                </div>
            </div>

            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal" onclick="addDevice()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
            </button>

            <button id="btn_delete" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#delcfmModelsSeUser" onclick="delSeDevice()">
                <span class="glyphicon glyphicon-remove" aria-hidden="true">移除</span>
            </button>
        </form>
    </div>
    <table id="tb_seDevice" style="table-layout: auto" class="table table-condensed table-bordered table-hover table-striped"></table>
</div>

<%@ include file="jeryCzc.jsp" %>
</body>
</html>
