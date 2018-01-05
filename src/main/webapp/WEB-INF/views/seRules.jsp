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
    <script src="<%=basePath%>resources/js/seRules.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#seRules_add").on("hidden.bs.modal", function () {
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
            $("#seRules_update").on("show.bs.modal", function () {
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
        <h3 class="panel-title">安全审计维护</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
            <div class="form-group" style="width:280px">
                <label class="col-sm-3 control-label" for="sdType">设备类型：</label>
                <div class="col-sm-8">
                    <select id="sdType" name="sdType" class="selectpicker show-tick form-control">
                        <option value="0" selected="selected">全部</option>
                        <option value="主机设备">主机设备</option>
                        <option value="终端设备">终端设备</option>
                    </select>
                </div>
            </div>
            <div class="form-group" style="width:280px; margin-left: 30px">
                <label class="col-sm-3 control-label" for="srType">审计类型：</label>
                <div class="col-sm-8">
                    <select id="srType" name="srType" class="selectpicker show-tick form-control">
                        <option value="10" selected="selected">全部</option>
                        <option value="0">TCP端口</option>
                        <option value="1">UDP端口</option>
                        <option value="2">软件</option>
                        <option value="3">漏洞/补丁</option>
                    </select>
                </div>
            </div>
            <button id="btn-search" type="button" class="btn btn-primary" style="margin-left: 20px;">
                <span class="glyphicon glyphicon-search" aria-hidden="true" onclick="findRules()">查询</span>
            </button>
            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#seRules_add">
                <span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
            </button>
            <button id="btn_modify" type="button" class="btn btn-primary"
                    data-target="#" onclick="seRulesUpdate()">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
            </button>
            <button id="btn_delete" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#delcfmModelsRules" onclick="delSeRules()">
                <span class="glyphicon glyphicon-remove" aria-hidden="true">删除</span>
            </button>
        </form>
    </div>
    <table id="tb_seRules" class="table table-striped"></table>
</div>


<!-- 安全审计规则新增modal -->

<div class="modal fade" id="seRules_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增安全审计规则</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label for="sdType_add" class="col-sm-3 control-label">设备类型：</label>
                        <div class="col-sm-7">
                            <select id="sdType_add" name="sdType_add" class="selectpicker show-tick form-control">
                                <option value="终端设备">终端设备</option>
                                <option value="主机设备">主机设备</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="No1">
                        <label class="col-sm-3 control-label" for="sdOs_add">操作系统：</label>
                        <div class="col-sm-7">
                            <select id="sdOs_add" name="sdOs_add" class="selectpicker show-tick form-control">
                                <option value="WINDOWS SERVER 2008">WINDOWS SERVER 2008</option>
                                <option value="WINDOWS 7">WINDOWS 7</option>
                                <option value="WINDOWS 8">WINDOWS 8</option>
                                <option value="WINDOWS 10">WINDOWS 10</option>
                                <option value="XP">XP</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="No2">
                        <label class="col-sm-3 control-label" for="sdOsType_add">系统版本：</label>
                        <div class="col-sm-7">
                            <select id="sdOsType_add" name="sdOsType_add" class="selectpicker show-tick form-control">
                                <option value="32-BIT">32-BIT</option>
                                <option value="64-BIT">64-BIT</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="srType_add">审计类型：</label>
                        <div class="col-sm-7">
                            <select id="srType_add" name="srType_add" class="selectpicker show-tick form-control">
                                <option value="0">TCP端口</option>
                                <option value="1">UDP端口</option>
                                <option value="2">软件</option>
                                <option value="3">漏洞/补丁</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="srRname_add" class="col-sm-3 control-label">审计内容：</label>
                        <div class="col-sm-7">
                            <textarea class="form-control" rows="3" id="srRname_add"
                                      placeholder="请输入审计内容" onkeydown='countChar("srRname_add","counter");'
                                      onkeyup='countChar("srRname_add","counter");' maxlength="120"></textarea><span
                                style="color: red; float: right">还可以输入<span id="counter">120</span>字</span>
                            </span>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>

                    <div class="form-group" id="No3">
                        <label class="col-sm-3 control-label" for="srRules_add1">审计规则：</label>
                        <div class="col-sm-7">
                            <select id="srRules_add1" name="srRules_add1" class="selectpicker show-tick form-control" >
                                <option value="1">必关闭</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="No4" style="display: none">
                        <label class="col-sm-3 control-label" for="srRules_add2">审计规则：</label>
                        <div class="col-sm-7">
                            <select id="srRules_add2" name="srRules_add2" class="selectpicker show-tick form-control" >
                                <option value="2">必安装</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="upload" onclick="addRules()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 安全审计规则修改modal -->

<div class="modal fade" id="seRules_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabe2">修改安全审计规则</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label for="sdType_update" class="col-sm-3 control-label">设备类型：</label>
                        <div class="col-sm-7">
                            <select id="sdType_update" name="sdType_update" class="selectpicker show-tick form-control">
                                <option value="终端设备">终端设备</option>
                                <option value="主机设备">主机设备</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="No11">
                        <label class="col-sm-3 control-label" for="sdOs_update">操作系统：</label>
                        <div class="col-sm-7">
                            <select id="sdOs_update" name="sdOs_update" class="selectpicker show-tick form-control">
                                <option value="WINDOWS SERVER 2008">WINDOWS SERVER 2008</option>
                                <option value="WINDOWS 7">WINDOWS 7</option>
                                <option value="WINDOWS 8">WINDOWS 8</option>
                                <option value="WINDOWS 10">WINDOWS 10</option>
                                <option value="XP">XP</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="No22">
                        <label class="col-sm-3 control-label" for="sdOsType_update">系统版本：</label>
                        <div class="col-sm-7">
                            <select id="sdOsType_update" name="sdOsType_update"
                                    class="selectpicker show-tick form-control">
                                <option value="32-BIT">32-BIT</option>
                                <option value="64-BIT">64-BIT</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="srType_update">审计类型：</label>
                        <div class="col-sm-7">
                            <select id="srType_update" name="srType_update" class="selectpicker show-tick form-control">
                                <option value="0">TCP端口</option>
                                <option value="1">UDP端口</option>
                                <option value="2">软件</option>
                                <option value="3">漏洞/补丁</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="srRname_update" class="col-sm-3 control-label">审计内容：</label>
                        <div class="col-sm-7">
                            <textarea class="form-control" rows="3" id="srRname_update"
                                      placeholder="请输入审计内容" onkeydown='countChar("srRname_update","counter1");'
                                      onkeyup='countChar("srRname_update","counter1");' maxlength="120"></textarea><span
                                style="color: red; float: right">还可以输入<span id="counter1">120</span>字</span>
                            </span>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>

                    <div class="form-group" id="No33">
                        <label class="col-sm-3 control-label" for="srRules_update1">审计规则：</label>
                        <div class="col-sm-7">
                            <select id="srRules_update1" name="srRules_update1"
                                    class="selectpicker show-tick form-control">
                                <option value="1">必关闭</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="No44" style="display: none">
                        <label class="col-sm-3 control-label" for="srRules_update2">审计规则：</label>
                        <div class="col-sm-7">
                            <select id="srRules_update2" name="srRules_update2"
                                    class="selectpicker show-tick form-control">
                                <option value="2">必安装</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateRules()">确定</button>
            </div>
        </div>
    </div>
</div>


<%@ include file="jeryCzc.jsp" %>
</body>
</html>
