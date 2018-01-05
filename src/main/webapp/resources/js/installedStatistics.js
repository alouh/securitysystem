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
    $("#siDate").val(currentdate);
    findInstalledStatistics();
});

function findInstalledStatistics() {
    var siIp = $("#siIp").val().trim();
    var sdType = $("#sdType").val();
    var siDate = $("#siDate").val();
    if (sdType == 0) {
        sdType = null;
    }
    var heightTable = document.documentElement.clientHeight - 34;
    $('#tb_installedStatstics').bootstrapTable('destroy');
    $("#tb_installedStatstics").bootstrapTable({
        url: './seInstalled/showInstalledStatistics',
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
                siIp: encodeURI(siIp),
                sdType: encodeURI(sdType),
                sdType1: sdType,
                stDate: encodeURI(siDate)
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
            /*     {field: 'state', checkbox: true, align: 'center', valign: 'middle'},*/
            {field: 'stId', title: 'id', visible: false, valign: 'middle'},
            {
                field: 'sdName', title: '设备名称', align: 'center'
            },
            {
                field: 'sdType', title: '设备类型', align: 'center'
            },
            {field: 'sdOs', title: '操作系统', align: 'center'},
            {field: 'sdOsType', title: '系统版本', align: 'center'},
            {field: 'sdUser', title: '使用人', align: 'center'},
            {field: 'sdIp', title: 'IP地址', align: 'center'},
            {field: 'sdMac', title: 'MAC地址', align: 'center'},
            {
                field: 'loopholeCount', title: '漏洞/补丁(个)', align: 'center',
                formatter: function (value, row, index) {
                    if (value == null || value == undefined || value == 0) {
                        return "0";
                    } else {
                        return '<a style="color:blue;" href="#" onclick="showModel(\'' + siDate + '\',' + 3+','+row.sdId+ ')">' + (value) + '</a>';
                    }
                }
            },
            {
                field: 'softwareCount', title: '软件(个)', align: 'center',
                formatter: function (value, row, index) {
                    if (value == null || value == undefined || value == 0) {
                        return "0";
                    } else {
                        return '<a style="color:blue;" href="#" onclick="showModel(\'' + siDate + '\',' + 2+','+row.sdId + ')">' + (value) + '</a>';
                    }
                }
            }
        ]
    });
}
function showModel(scDate,srType,sdId) {
    $("#detailed_Statistics").modal("show");
    var heightTable = document.documentElement.clientHeight - 200;
    $('#tb_detailStatistics').bootstrapTable('destroy');
    $("#tb_detailStatistics").bootstrapTable({
        url: './seInstalled/showDetailStatistics',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded", // POST请求需要 编码
     //   toolbar: '#toolbar',
        striped: false, // 是否显示行间隔色
        cache: false,
        height: heightTable,
        pagination: true,
        sortOrder: 'desc',
        queryParams: function (params) {
            return {
                pageindex: this.pageNumber,
                pageSize: params.limit,
                scDate: scDate,
                sdId: sdId,
                srType: srType
            }
        },
        silent: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [20, 50, 200],
        strictSearch: true,
        clickToSelect: true,
        uniqueId: 'stId',
        cardView: false,
        detailView: false,
        columns: [
            {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'stId', title: 'id', visible: false, valign: 'middle'},
            {field: 'sdName', title: '设备名称', align: 'center'},

            {field: 'sdIp', title: 'IP地址', align: 'center'},
            {field: 'sdMac', title: 'Mac地址', align: 'center'},
            {field: 'stReason', title: '异常原因', align: 'center'},
            {field: 'srRname', title: '审计内容', align: 'center'},
            {
                field: 'stDate', title: '审计日期', align: 'center',
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
        /*  if (hour < 10) {
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