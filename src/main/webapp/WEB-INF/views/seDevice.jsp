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
    <script type="text/javascript">
        $(function () {
            $("#seDevice_add").on("hidden.bs.modal", function () {
                $("#sdName_add").val("");
                $('#sdType_add').selectpicker("val", "主机设备");
                $('#sdOs_add').selectpicker("val", "WINDOWS SERVER 2012");
                $('#sdOsType_add').selectpicker("val", "32-BIT");
                $("#sdMac_add").val("");
                $("#sdDept_add").val("");
                $("#sdUser_add").val("");
                $("#sdIp_add").val("");
                $("#No1").show();
                $("#No2").show();
                $("#No3").show();
            });

        });

    </script>
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
            <div class="form-group">
                <div class="col-sm-8">
                    <input class="form-control" id="sdUser" type="hidden"/>
                </div>
            </div>

            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#seDevice_add">
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


<!-- 设备新增台账modal -->

<div class="modal fade" id="seDevice_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增USB设备类型</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label for="sdType_add" class="col-sm-3 control-label">USB设备类型：</label>
                        <div class="col-sm-7">
                            <select id="sdType_add" name="sdType_add" class="selectpicker show-tick form-control">
                                <option value="便携设备">便携设备</option>
                                <option value="无线WiFi">无线WiFi</option>
                                <option value="大容量存储设备">大容量存储设备</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" id="upload" onclick="addDevice()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--<!-- 信息删除确认modal -->
<div class="modal fade" id="delcfmModels">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-success" data-dismiss="modal" onclick="delDevice()">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 信息提示modal -->
<div class="modal fade" id="message">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="messageText"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-success" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>--%>
<%@ include file="jeryCzc.jsp" %>
</body>
</html>
