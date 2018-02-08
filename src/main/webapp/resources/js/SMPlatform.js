$(function () {
    findSMPlatform();
});

/**
 * @return {string}
 */
var GetQueryString = function (key) {
    var search = decodeURIComponent(location.search);
    var reg = new RegExp(".*" + key + "\\=" + "([^&]*)(&?.*|)", "g");
    return search.replace(reg, "$1");
};
/**
 * 展示短信配置平台
 */
function findSMPlatform() {

    var pn_Number = $("#pn_Number_add").val();
    var heightTable = document.documentElement.clientHeight - 45;

    $('#tb_sMPlatform').bootstrapTable('destroy');
    $("#tb_sMPlatform").bootstrapTable({
        url: './sMPlatform/showSMPlatform',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded", // POST请求需要 编码
        toolbar: '#toolbar',
        striped: false, // 是否显示行间隔色
        cache: false,
        height: heightTable,
        pagination: true,
        sortOrder: 'desc',
        queryParams: function (params) {
            return {
                pageIndex: this.pageNumber,
                pageSize: params.limit,
                pn_Number: pn_Number/*,
                siId: encodeURI(siId)*/
            }
        },
        silent: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [20, 50, 200],
        strictSearch: true,
        clickToSelect: true,
        uniqueId: 'siId',
        cardView: false,
        detailView: false,
        columns: [
            {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'pn_Id', title: 'id', visible: false, valign: 'middle'},
            {field: 'pn_Number', title: '通知号码', align: 'center'},
            {
                field: 'pn_Date', title: '维护日期', align: 'center',
                formatter: function (value) {
                    return get_Date(value);
                }
            }
        ]
    });

}

/**
 * 新增号码
 */
function addPhoneNumber() {
    var number = $("#pn_Number_add").val();

    if (!checkMobile(number)){
        $("#messageText").text("请输入正确的手机号码");
        $("#message").modal("show");
        return false;
    }

    $.ajax({
        url: './sMPlatform/addPhoneNumber',
        type: 'post',
        data: {
            pn_Number: number
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#sMPlatform_add").modal("hide");
                pop("消息提示","新增成功","2000");
                findSMPlatform();
            } else {
                $("#messageText").text(result.msg);
                $("#message").modal("show");
                return false;
            }
        }
    });

}

/**
 * 修改号码
 */
function updatePhoneNumber() {

    var rows = $("#tb_sMPlatform").bootstrapTable('getSelections');

    if (rows.length !== 1) {
        $("#messageText").text("请选择一条数据进行修改！");
        $("#message").modal("show");
        return false;
    }
    getUpdateDate();
    $("#sMPlatform_update").modal("show");
}

function getUpdateDate() {
    var rows = $("#tb_sMPlatform").bootstrapTable('getSelections');
    var id = rows[0].pn_Id;

    $.ajax({
        url: './sMPlatform/getUpdateDate',
        type: 'post',
        data: {
            selectedId:id
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                document.getElementById("pn_Number_update").value = result.number;
            } else {
                $("#messageText").text(result.msg);
                $("#message").modal("show");
                return false;
            }
        }
    });
}
function update() {

    var rows = $("#tb_sMPlatform").bootstrapTable('getSelections');
    var id = rows[0].pn_Id;
    var number = $("#pn_Number_update").val();

    if (!checkMobile(number)){
        $("#messageText").text("请输入正确的手机号码");
        $("#message").modal("show");
        return false;
    }

    $.ajax({
        url: './sMPlatform/updatePhoneNumber',
        type: 'post',
        data: {
            pn_Number: number,
            selectedId:id
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#updateModels").modal("hide");
                $("#sMPlatform_update").modal("hide");
                pop("消息提示","修改成功","2000");
                findSMPlatform();
            } else {
                $("#messageText").text(result.msg);
                $("#message").modal("show");
                return false;
            }
        }
    });

}

/**
 * 批量刪除
 */
function delTypeList() {
    var rows = $("#tb_sMPlatform").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $("#messageText").text("请选择一条数据进行删除！");
        $("#message").modal("show");
        return false;
    }
    $("#delcfmModels").modal("show");
}

function dels() {
    var rows = $("#tb_sMPlatform").bootstrapTable('getSelections');
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        ids += "," + rows[i].pn_Id;
    }
    $.ajax({
        url: './sMPlatform/delDeviceByIds',
        type: 'post',
        data: {'pn_Ids': ids.substring(1)},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#delcfmModels").modal("hide");
                pop("消息提示","删除成功","2000");
                findSMPlatform();
            } else {
                $("#messageText").text(result.msg);
                pop("消息提示","删除失败","2000");
                $("#message").modal("show");
                return false;
            }
        }
    })
}

/**
 * date转换成String
 * @param time
 * @returns {string}
 */
function get_Date(time) {
    if (time === undefined || time == null || time === "") {
        return "";
    } else {
        var date = new Date(time);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;    //js从0开始取
        var day = date.getDate();
        var str = "";
        str += year;
        if (month < 10) {
            str += ("-0" + month);
        } else {
            str += ("-" + month);
        }
        if (day < 10) {
            str += ("-0" + day);
        } else {
            str += ("-" + day);
        }
        return str;
    }
}

/**
 * 检测手机号码格式
 */
function checkMobile(phoneNumber){
    var reg = /^1[34578]\d{9}$/;

    return reg.test(phoneNumber);
}