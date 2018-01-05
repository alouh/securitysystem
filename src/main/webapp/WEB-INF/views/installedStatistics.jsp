<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-27
  Time: 下午 2:48
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
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/bootstrap-datetimepicker.min.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/installedStatistics.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript">
        $(function () {
            var picker1 = $('#siDate').datetimepicker({
                format: 'yyyy-mm-dd',
                language: 'zh-CN',
                minView: "month",
                autoclose: true
            });
        });
    </script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">软件、漏洞/补丁异常信息</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="siIp">IP地址:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="siIp" type="text"/>
                </div>
            </div>
            <div class="form-group" style="width:280px; margin-left: 30px">
                <label class="col-sm-3 control-label" for="sdType">设备类型</label>
                <div class="col-sm-8">
                    <select id="sdType" name="sdType" class="selectpicker show-tick form-control">
                        <option value="0" selected="selected">全部</option>
                        <option value="主机设备">主机设备</option>
                        <option value="终端设备">终端设备</option>
                    </select>
                </div>
            </div>

          <%--  <div class="form-group" style="width:280px; margin-left: 30px">
                <label class="col-sm-3 control-label" for="siType">类型</label>
                <div class="col-sm-8">
                    <select id="siType" name="siType" class="selectpicker show-tick form-control">
                        <option value="0" selected="selected">全部</option>
                        <option value="漏洞/补丁">漏洞/补丁</option>
                        <option value="软件">软件</option>
                    </select>
                </div>
            </div>--%>

            <div class="form-group">
                <label for="siDate" class="col-sm-3 control-label">时间：</label>
                <div class="col-sm-7">
                    <input name="siDate" id="siDate" type='text' class="form-control input-sm" readonly="readonly"
                           style="width: 210px"/>
                </div>
            </div>

            <button id="btn-search" type="button" class="btn btn-primary" style="margin-left: 40px;">
                <span class="glyphicon glyphicon-search" aria-hidden="true"
                      onclick="findInstalledStatistics()">查询</span>
            </button>
        </form>
    </div>
    <table id="tb_installedStatstics" class="table table-striped"></table>
</div>


<!-- 异常详情详情 -->
<div class="modal fade" id="detailed_Statistics" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document" style="width: 1300px; height: 720px">
        <div class="modal-content">
            <div class="col-sm-12" id="search">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                        <h3 class="panel-title">异常详情</h3>
                    </div>
                    <div class="panel-body" id="toolbar1">
                    </div>
                    <table id="tb_detailStatistics" class="table table-striped"></table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
