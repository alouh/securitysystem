<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2017/8/14 0014
Time: 11:16
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit">
    <title>网络及信息系统安全过程管理工具</title>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/admin.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/app.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/css/homes.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/jquery-easyui-1.4/themes/default/easyui.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/plug/jquery-easyui-1.4/themes/icon.css"/>
    <script src="<%=basePath%>resources/plug/jquery/jquery.min.js"></script>
    <script language="JavaScript" src="<%=basePath%>resources/plug/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>resources/plug/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/plug/jquery-easyui-1.4/jquery.easyui.min.js"></script>
    <script src="<%=basePath%>resources/plug/cookie.js"></script>
   <%-- <script src="<%=basePath%>resources/js/home.js"></script>--%>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame"/>
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="left" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
        <frame src="illegalDevice" name="rightFrame" id="rightFrame" title="rightFrame"/>
    </frameset>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>