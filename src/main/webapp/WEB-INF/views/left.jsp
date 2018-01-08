<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-16
  Time: 下午 7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit">
    <title>无标题文档</title>
    <link href="<%=basePath%>resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="<%=basePath%>resources/plug/jquery/jquery.js"></script>
    <script src="<%=basePath%>resources/plug/jquery-easyui-1.4/jquery.easyui.min.js"></script>
    <%-- <script src="<%=basePath%>resources/js/left.js"></script>--%>
    <script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson li").click(function () {
                $(".menuson li.active").removeClass("active");
                $(this).addClass("active");
            });

            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('ul').slideUp();
                } else {
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>
</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>功能</div>

<dl class="leftmenu">
    <dd>
        <div class="title">
            <span><img src="<%=basePath%>resources/images/leftico01.png"/></span>首页
        </div>
        <ul class="menuson">
            <%--<li class="active"><cite></cite>
                <a href="index" target="rightFrame">首页</a><i></i>
            </li>--%>
            <li class="active"><cite></cite>
                <a href="seRules" target="rightFrame">外接非法USB设备终端</a><i></i>
            </li>
        </ul>
    </dd>

    <%--<dd>
        <div class="title">
            <span><img src="<%=basePath%>resources/images/leftico02.png"/></span>系统管理
        </div>
        <ul class="menuson">
            <li><cite></cite>
                <a href="seUser" target="rightFrame">账号管理</a><i></i></li>

        </ul>
    </dd>--%>

    <dd>
        <div class="title"><span><img src="<%=basePath%>resources/images/leftico03.png"/></span>参数配置</div>
        <ul class="menuson">
            <li><cite></cite>
                <a href="seDevice" target="rightFrame">白名单配置</a><i></i>
            </li>
            <li><cite></cite>
                <a href="seInstalled" target="rightFrame">短信平台配置</a><i></i>
            </li>
        </ul>
    </dd>

    <%--<dd>
        <div class="title"><span><img src="<%=basePath%>resources/images/leftico04.png"/></span>功能管理</div>
        <ul class="menuson">
            <li><cite></cite>
                <a href="seInstalled" target="rightFrame">短信平台</a><i></i>
            </li>
        </ul>

    </dd>--%>

    <%--<dd>
        <div class="title"><span><img src="<%=basePath%>resources/images/leftico04.png"/></span>安全管理</div>
        <ul class="menuson">
            <li><cite></cite>
                <a href="seRules" target="rightFrame">外接非法USB设备终端</a><i></i>
            </li>
        </ul>

    </dd>--%>
</dl>

</body>

</html>