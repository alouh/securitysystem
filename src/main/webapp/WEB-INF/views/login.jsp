<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <title>网络及信息系统安全过程管理工具</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/images/logo.ico">
    <link rel="stylesheet" href="<%=basePath%>resources/css/reset.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/register.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery/jquery.validate.js"></script>
    <script src="<%=basePath%>resources/plug/jsencrypt.js"></script>
    <script src="<%=basePath%>resources/plug/cookie.js"></script>
    <script src="<%=basePath%>resources/plug/md5.js"></script>
    <script src="<%=basePath%>resources/js/login.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#password_update").on("hidden.bs.modal", function () {
                $("#oldPassword").val("");
                $("#newPassword").val("");
                $("#newPasswordAgain").val("");
            });

            $("#password_update").on("show.bs.modal", function () {
                $("#oldPassword").val("");
                $("#newPassword").val("");
                $("#newPasswordAgain").val("");
            });
        })
    </script>
</head>
<body>
<div class="top">
    <div class="top_left_box fl">
        <img src="<%=basePath%>resources/images/top_logo_left.png"/>
    </div>
    <div class="top_right_box fr">
        <img src="<%=basePath%>resources/images/top_logo_right.png"/>
    </div>
</div>
<div class="register_box">
    <div class="divisionLine"></div>
    <div class="registerBox_position" style="">
        <div class="register_sbox">
            <div class="box">
                <div class="box_hui">
                    <img src="<%=basePath%>resources/images/log_hui.png" style="height: 20px;"/>
                </div>
                <div class="login_form">
                    <!--用户名-->
                    <div class="hh">
                        <p>账&nbsp;&nbsp;号</p>
                        <div class="name">
                            <label>
                                <i></i>
                                <input type="text" id="userName" name="userName" autocomplete="off"
                                       placeholder="请输入您的用户名"/></label>
                        </div>
                    </div>
                    <!--密码-->
                    <div class="hh">
                        <p>密&nbsp;&nbsp;码</p>
                        <div class="pwd">
                            <label>
                                <i></i>
                                <input type="password" id="pwd" name="pwd" placeholder="请输入您的密码"/>
                            </label>
                        </div>
                    </div>
                    <div class="dl"><input type="submit" value="登&nbsp;&nbsp;录" id="btn"/></div>
                    <div class="login-error" style="display: none;">
                        <img src="<%=basePath%>resources/images/login-error.png">账户名和密码不匹配，请重新输入!
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- 修改密码modal -->
<div class="modal fade" id="password_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_update"
     data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel_update">密码修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="oldPassword" class="col-sm-3 control-label">原始密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="oldPassword" placeholder="请输入原始密码"/>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="newPassword" placeholder="请输入新密码"/>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>
                    <div class="form-group">
                        <label for="newPasswordAgain" class="col-sm-3 control-label">确认新密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="newPasswordAgain" placeholder="请输入新密码"/>
                        </div>
                        <h4 style="color: red;">*</h4>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updatePwd()">确定</button>
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

<div class="modal fade" id="landMessage">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="land"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-success" onclick="firmLand()">确定</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
