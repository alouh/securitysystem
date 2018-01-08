$(function () {
    findSeInstalled();
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
 * 软件、漏洞/补丁安装明细
 */
function findSeInstalled() {
    var siId = GetQueryString("id");
    var siSname = $("#siSname_add").val().trim();
    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_seInstalled').bootstrapTable('destroy');
    $("#tb_seInstalled").bootstrapTable({
        url: './seInstalled/showInstalled',
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
                pageindex: this.pageNumber,
                pageSize: params.limit,
                siSname: encodeURI(siSname),
                siId: encodeURI(siId)
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
            {field: 'siId', title: '明细id', visible: false, valign: 'middle'},
            {field: 'siSname', title: '通知号码1', align: 'center',width: '120px'},
            {
                field: 'siDate', title: '维护日期', align: 'center', width: '120px',
                formatter: function (value) {
                    return get_Date(value);
                }
            }
        ]
    });

}


/**
 * 新增设备
 */
function addDevice() {
    var siSname = $("#siSname_add").val();

    $.ajax({
        url: './seInstalled/addDevice',
        type: 'post',
        data: {
            'siSname': siSname
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#seDevice_add").modal("hide");
                pop("消息提示","新增成功","2000");
                findSeInstalled();
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
function delSeDevice() {
    var rows = $("#tb_seInstalled").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $("#messageText").text("请选择一条数据进行删除！");
        $("#message").modal("show");
        return false;
    }
    $("#delcfmModels").modal("show");
}
function dels() {
    var rows = $("#tb_seInstalled").bootstrapTable('getSelections');
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        ids += "," + rows[i].siId;
    }
    $.ajax({
        url: './seInstalled/delDeviceByIds',
        type: 'post',
        data: {'siIds': ids.substring(1)},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#delcfmModels").modal("hide");
                pop("消息提示","删除成功","2000");
                findSeInstalled();
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
        var hour = date.getHours();
        var minutes = date.getMinutes();
        var second = date.getSeconds();
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
        /*     if (hour < 10) {
         str += ("&nbsp;0" + hour);
         } else {
         str += ("&nbsp;" + hour);
         }
         if (minutes < 10) {
         str += ("&nbsp;:0" + minutes);
         } else {
         str += ("&nbsp;:" + minutes);
         }*/
        return str;
    }
}