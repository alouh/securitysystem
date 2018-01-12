<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-24
  Time: 下午 5:32
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
    <title>短信平台配置</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/jeryCzc.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/jeryCzc.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/SMPlatform.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">短信平台配置</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
            <%--<div class="form-group">
                <label class="col-sm-3 control-label" for="siSname_add">通知号码:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="siSname_add" type="text"/>
                </div>
            </div>--%>

            <%--<button id="btn-add" type="button" class="btn btn-primary" style="margin-left: 50px;">
                <span class="glyphicon glyphicon-plus" aria-hidden="true" onclick="addDevice()">新增</span>
            </button>--%>

            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#sMPlatform_add">
                <span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
            </button>

            <button id="btn_delete" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#delcfmModelsSeUser" onclick="delTypeList()">
                <span class="glyphicon glyphicon-remove" aria-hidden="true">移除</span>
            </button>
        </form>
    </div>
    <table id="tb_sMPlatform" class="table table-striped"></table>
</div>

<%--新增短信平台号码--%>
<div class="modal fade" id="sMPlatform_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增短信平台号码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="pn_Number_add">手机号码：</label>
                        <div class="col-sm-8">
                            <input id="pn_Number_add" class="form-control" type="text"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="upload" onclick="addPhoneNumber()">确定</button>
            </div>
        </div>
    </div>
</div>


<%@ include file="jeryCzc.jsp" %>
</body>
</html>
