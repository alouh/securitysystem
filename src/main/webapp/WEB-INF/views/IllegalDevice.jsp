<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-27
  Time: 下午 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript">

        $(function () {
            $("#illegalDevice_add").on("hidden.bs.modal", function () {
                $('#sdType_add').selectpicker("val", "终端设备");
                $('#sdOs_add').selectpicker("val", "WINDOWS SERVER 2008");
                $('#sdOsType_add').selectpicker("val", "32-BIT");
                $('#srType_add').selectpicker("val", "0");
                $('#srRules_add1').selectpicker("val", "1");
                $('#srRules_add2').selectpicker("val", "2");
                $("#No1").show();
                $("#No2").show();
                $("#No3").show();
                $("#No4").hide();
                $("#srRname_add").val("");
                document.getElementById("counter").innerHTML = 120 - document.getElementById("srRname_add").value.length;
            });
            $("#illegalDevice_update").on("show.bs.modal", function () {
                document.getElementById("counter1").innerHTML = 120 - document.getElementById("srRname_update").value.length;
            });


        });
        function countChar(textareaName, spanName) {
            document.getElementById(spanName).innerHTML = 120 - document.getElementById(textareaName).value.length;
        }
    </script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">外接非法USB设备终端</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
            <div class="form-group" style="width:400px">
                <label class="col-sm-3 control-label" for="sdType">USB设备类型：</label>
                <div class="col-sm-8">
                    <select id="sdType" name="sdType" class="selectpicker show-tick form-control">
                        <option value="0" selected="selected">全部</option>
                        <option value="便携设备" >便携设备</option>
                        <option value="无线WiFi">无线WiFi</option>
                        <option value="大容量存储设备">大容量存储设备</option>
                    </select>
                </div>
            </div>
            <div class="form-group" style="width:280px; margin-left: 30px">
                <label class="col-sm-3 control-label" for="srType">终端IP：</label>
                <div class="col-sm-8">
                    <input class="form-control" id="srType" type="text"/>
                </div>
            </div>
            <button id="btn-search" type="button" class="btn btn-primary" style="margin-left: 20px;">
                <span class="glyphicon glyphicon-search" aria-hidden="true" onclick="findRules()">查询</span>
            </button>
        </form>
    </div>
    <table id="tb_illegalDevice" class="table table-striped"></table>
</div>

<%@ include file="jeryCzc.jsp" %>
</body>
</html>
