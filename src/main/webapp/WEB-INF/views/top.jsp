<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-16
  Time: 下午 7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
    <title>无标题文档</title>

    <link href="<%=basePath%>resources/css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="<%=basePath%>resources/plug/jquery/jquery.js"></script>
    <script language="JavaScript" src="<%=basePath%>resources/plug/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>resources/plug/cookie.js"></script>
    <script src="<%=basePath%>resources/js/top.js"></script>

</head>
<body style="background:url(<%=basePath%>resources/images/topbg.gif) repeat-x;">

<div class="topleft">
    <a href="home" target="_parent">
        <img src="<%=basePath%>resources/images/logo3.png" title="系统首页" />
    </a>
        <div class="logo_name">网络及信息系统安全过程管理</div>
</div>

<ul class="nav">

</ul>

<div class="topright">
    <ul>
        <li>
            <a href="/securitysystem/updatePwd" onclick="updatepwd()" target="rightFrame"><span class="changepwd">修改密码</span></a>
        </li>
        <li>
            <a href="#" onclick="logout()" target="_parent">  <span class="logout">注销</span></a>
        </li>
    </ul>

    <div class="user">
        <span>${sessionScope.userName}</span>
    </div>

</div>
</body>

</html>