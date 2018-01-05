var GetQueryString = function (key) {
    var search = decodeURIComponent(location.search);
    var reg = new RegExp(".*" + key + "\\=" + "([^&]*)(&?.*|)", "g");
    return search.replace(reg, "$1");
}
$(function () {
    findSeInstalled();
});
/**
 * 软件、漏洞/补丁安装明细
 */
function findSeInstalled() {
    var sdId = GetQueryString("id");
    var siSname = $("#siSname").val().trim();
    var siType = $("#siType").val();
    if (siType == 0) {
        siType = null;
    }
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
                siType: encodeURI(siType),
                siType1: siType,
                siSname: encodeURI(siSname),
                sdId: encodeURI(sdId)
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
            {field: 'siId', title: '明细id', visible: false, valign: 'middle'},
            {
                field: 'sdName', title: '设备名称', align: 'center', width: '150px'
            },
            {field: 'sdIp', title: 'IP地址', align: 'center', width: '150px'},
            {field: 'siType', title: '类型', align: 'center', width: '120px'},
            {field: 'siSname', title: '名称', align: 'center',width: '320px'},
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

function returnDevice() {
    window.location.href = "seDevice";
}