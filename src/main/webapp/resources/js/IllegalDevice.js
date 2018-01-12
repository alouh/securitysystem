$(function () {
    findRules();
});

/**
 * 展示安全审计规则列表框
 */
function findRules() {

    var id_Ip = $("#id_Ip").val();

    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_illegalDevice').bootstrapTable('destroy');
    $("#tb_illegalDevice").bootstrapTable({
        url: './illegalDevice/showRules',
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
                id_Ip:id_Ip
            }
        },
        silent: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [20, 50, 200],
        strictSearch: true,
        clickToSelect: true,
        uniqueId: 'id_Id',
        cardView: false,
        detailView: false,
        columns: [
            {field: 'id_Id', title: 'id', visible: false, valign: 'middle'},
            {field: 'id_Type', title: 'USB设备类型', align: 'center'},
            {field: 'id_Ip', title: '网络地址', align: 'center'},
            {field: 'id_Mac', title: '物理地址', align: 'center'},
            {field: 'id_UsrName', title: '用户名称', align: 'center'},
            {field: 'id_HostName', title: '主机名称', align: 'center'},
            {
                field: 'id_Date', title: '接入日期', align: 'center',
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
