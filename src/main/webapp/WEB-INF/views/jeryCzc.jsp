<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-12-1
  Time: 下午 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="pop" class="panel panel-default pop" hidden>
    <div id="popHead" class="popHead">
        <a class="popClose" title="关闭" onclick="closePop()">关闭</a>
        <span></span>
    </div>
    <div id="popTxt" class="popContext"></div>
</div>

<%--修改信息--%>
<div class="modal fade" id="updateModels">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要修改吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-success" data-dismiss="modal" onclick="update()">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 批量信息删除确认modal -->
<div class="modal fade" id="delcfmModels">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-success" data-dismiss="modal" onclick="dels()">确定</button>
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
                <button class="btn btn-success" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
