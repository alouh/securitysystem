<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-28
  Time: 下午 4:50
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
    <title>设备台账管理</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/seStatistics.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath%>resources/plug/highcharts.js"></script>

    <script type="text/javascript">
        $(function () {
            var startDate = $('#startDate').datetimepicker({
                format: 'yyyy-mm-dd',
                language: 'zh-CN',
                minView: "month",
                autoclose: true,
                initialDate: new Date()
            });
            $('#endDate').datetimepicker({
                format: 'yyyy-mm-dd',
                language: 'zh-CN',
                minView: "month",
                autoclose: true
            });
            $("#detailed_information").on("hidden.bs.modal", function () {
                $("#sdIp").val("");
                $("#srRname").val("");
            });
        });

    </script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">安全审计分析</h3>
    </div>
    <div class="panel-body">
        <form role="formSearch" class="form-inline">
            <div class="form-group" style="width: 280px">
                <label for="startDate" class="col-sm-3 control-label">开始时间：</label>
                <div class="col-sm-7">
                    <input name="startDate" id="startDate" type='text' class="form-control input-sm"
                           readonly="readonly" style="width: 210px"/>
                </div>
            </div>

            <div class="form-group" style="width: 280px;margin-left: 20px">
                <label for="endDate" class="col-sm-3 control-label">结束时间：</label>
                <div class="col-sm-7">
                    <input name="endDate" id="endDate" type='text' class="form-control input-sm"
                           readonly="readonly" style="width: 210px"/>
                </div>
            </div>
            <div class="form-group" style="width:280px; margin-left: 30px">
                <label class="col-sm-3 control-label" for="sdType">设备类型：</label>
                <div class="col-sm-8">
                    <select id="sdType" name="sdType" class="selectpicker show-tick form-control">
                        <option value="终端设备">终端设备</option>
                        <option value="主机设备">主机设备</option>
                    </select>
                </div>
            </div>
            <button id="btn-search" type="button" class="btn btn-primary" style="margin-left: 20px;">
                <span class="glyphicon glyphicon-search" aria-hidden="true" onclick="findAll()">查询</span>
            </button>
        </form>
    </div>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#tab1" data-toggle="tab">
                异常趋势图
            </a>
        </li>
        <li><a href="#tab2" data-toggle="tab">终端/主机设备安全异常统计</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">

        <div class="tab-pane fade in active" id="tab1">
            <div id="myChart" style="width: 98%;height:700px;"></div>
        </div>

        <div class="tab-pane fade" id="tab2">
            <table id="tb_seStatistics" class="table table-striped"></table>
        </div>


    </div>
</div>

<!-- 安全审计详情 -->
<div class="modal fade" id="detailed_information" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document" style="width: 1300px; height: 720px">
        <div class="modal-content">
            <div class="col-sm-12" id="search">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                        <h3 class="panel-title">安全审计详情</h3>
                    </div>
                    <div class="panel-body" id="toolbar1">
                        <form role="formSearch" class="form-inline">
                            <div class="form-group" style="margin-left: 10px">
                                <label class="col-sm-3 control-label" for="sdIp">IP地址：</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="sdIp" type="text"/>
                                </div>
                                <input class="form-control" id="scDate" type="text" style="display: none"/>
                                <input class="form-control" id="sdType1" type="text" style="display: none"/>
                                <input class="form-control" id="srType" type="text" style="display: none"/>
                            </div>
                            <div class="form-group" style="width: 280px;margin-left: 30px">
                                <label class="col-sm-3 control-label" for="srRname">审计内容：</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="srRname" type="text"/>
                                </div>
                            </div>
                            <button id="btn-search1" type="button" class="btn btn-default" style="margin-left: 20px;"
                                    onclick="showDetailMsg()">
                                <span class="glyphicon glyphicon-search" aria-hidden="true">查询</span>
                            </button>
                        </form>
                    </div>
                    <table id="tb_detailStatistics" class="table table-striped"></table>
                </div>
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
</div>
</body>
</html>
