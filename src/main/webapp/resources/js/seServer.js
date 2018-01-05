$(function () {
    var seperator1 = "-";
    var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    $("#svDate").val(currentdate);
    findServer();
});
/**
 * 展示网络设备服务开放列表
 */
function findServer() {
  /*  var sdIp = $("#sdIp").val().trim();*/
    var type = $("#type").val();
    var svDate = $("#svDate").val();
    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_server').bootstrapTable('destroy');
    $("#tb_server").bootstrapTable({
        url: './seServer/showServer',
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
             /*   sdIp: sdIp,*/
                type: type,
                svDate: encodeURI(svDate)
            }
        },
        silent: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [20, 50, 200],
        strictSearch: true,
        clickToSelect: true,
        uniqueId: 'userId',
        cardView: false,
        detailView: false,
        columns: [
            {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'svId', title: 'id', visible: false, valign: 'middle'},
            {field: 'sdName', title: '设备名称', align: 'center'},
            {field: 'sdIp', title: '设备IP', align: 'center'},
            {field: 'sdUser', title: '使用人', align: 'center'},
            {
                field: 'svTelnet', title: 'Telnet服务', align: 'center',
                formatter: function (value) {
                    if (value == 0) {
                        return "关闭";
                    } else if (value == 1) {
                        return "开启";
                    } else {
                        return value;
                    }
                }
            },
            {field: 'svSsh', title: 'SSH服务', align: 'center',
                formatter: function (value) {
                    if (value == 0) {
                        return "关闭";
                    } else if (value == 1) {
                        return "开启";
                    } else {
                        return value;
                    }
                }
            },
            {field: 'svHttp', title: 'HTTP服务', align: 'center',
                formatter: function (value) {
                    if (value == 0) {
                        return "关闭";
                    } else if (value == 1) {
                        return "开启";
                    } else {
                        return value;
                    }
                }},
            {
                field: 'svDate', title: '扫描时间', align: 'center',
                formatter: function (value) {
                    return get_Date(value);
                }
            }
        ]
    });

}


/**
 * date转换成String
 * @param time
 * @returns {string}
 */
function get_Date(time) {
    if (time == undefined || time == null || time == "") {
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
     /*   if (hour < 10) {
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