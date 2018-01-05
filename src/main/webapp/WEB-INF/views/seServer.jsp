<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-24
  Time: 下午 1:56
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
    <title>网络设备服务开放详情</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/seServer.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript">
        $(function () {
            var picker1 = $('#svDate').datetimepicker({
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
        <h3 class="panel-title">网络设备服务开放详情</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
          <%--  <div class="form-group">
                <label class="col-sm-3 control-label" for="sdIp">IP地址:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="sdIp" type="text"/>
                </div>
            </div>--%>
            <div class="form-group" style="width:280px;">
                <label class="col-sm-3 control-label" for="type">服务状态：</label>
                <div class="col-sm-8">
                    <select id="type" name="type" class="selectpicker show-tick form-control">
                        <option value="0" selected="selected">全部</option>
                        <option value="1">HTTP开启</option>
                        <option value="2">HTTP关闭</option>
                        <option value="3">SSH开启</option>
                        <option value="4">SSH关闭</option>
                        <option value="5">Telnet关闭</option>
                        <option value="6">Telnet开放</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="svDate" class="col-sm-3 control-label">时间：</label>
                <div class="col-sm-7">
                    <input name="svDate" id="svDate" type='text' class="form-control input-sm"  readonly="readonly" style="width: 210px"/>
                </div>
            </div>

            <button id="btn-search" type="button" class="btn btn-primary" style="margin-left: 50px;">
                <span class="glyphicon glyphicon-search" aria-hidden="true" onclick="findServer()">查询</span>
            </button>
        </form>
    </div>
    <table id="tb_server" class="table table-striped"></table>
</div>

</body>
</html>
