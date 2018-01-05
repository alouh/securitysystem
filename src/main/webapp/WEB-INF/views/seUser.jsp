<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-16
  Time: 下午 3:40
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
    <title>人员管理</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/css/bootstrap-select.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/jeryCzc.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.form.js"></script>
    <script src="<%=basePath%>resources/plug/jeryCzc.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>resources/js/seUser.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-select-1.12.4/js/i18n/defaults-zh_CN.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#user_add").on("hidden.bs.modal", function () {
                $("#userName_add").val("");
                $("#realName_add").val("");
                /* $("#sex_add").selectpicker("val", "");*/
            });

        });

    </script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">人员管理</h3>
    </div>
    <div class="panel-body" id="toolbar">
        <form role="formSearch" class="form-inline">
            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#user_add">
                <span class="glyphicon glyphicon-plus" aria-hidden="true">新增</span>
            </button>
            <button id="btn_delete" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#delcfmModelsSeUser" onclick="delSeUser()">
                <span class="glyphicon glyphicon-remove" aria-hidden="true">删除</span>
            </button>
        </form>
    </div>
    <table id="tb_seUser" class="table table-striped"></table>
</div>


<!-- 人员新增modal -->

<div class="modal fade" id="user_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">人员新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="add_user_form" method="post"
                >
                    <div class="form-group">
                        <label for="userName_add" class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="userName_add" name="userName_add"
                                   placeholder="请输入用户名" maxlength="25"/>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>
                    <div class="form-group">
                        <label for="realName_add" class="col-sm-3 control-label">真实姓名：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="realName_add" name="realName_add"
                                   placeholder="请输入真实姓名" maxlength="40"/>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>
                    <%--  <div class="form-group">
                          <label for="sex_add" class="col-sm-3 control-label">性别：</label>
                          <div class="col-sm-7">
                              <select id="sex_add" name="sex_add"
                                      class="selectpicker show-tick form-control" data-size="5">
                                  <option value="0">男</option>
                                  <option value="1">女</option>
                              </select>
                          </div>
                          <h4 style="color: red;">*</h4>
                      </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="upload" onclick="addUser()">确定</button>
            </div>
        </div>
    </div>
</div>



<%@ include file="jeryCzc.jsp" %>

</body>
</html>
