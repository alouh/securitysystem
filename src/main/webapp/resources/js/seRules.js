$(function () {
    findRules();
});
/**
 * 新增下拉框数据切换
 */
$(function () {
/*    $("#sdType_add").change(function () {
        var sdType = $("#sdType_add").val();
        if ("主机设备" == sdType) {
            $("#No1").hide();
            $("#No2").hide();
        } else {
            $("#No1").show();
            $("#No2").show();
        }
    });*/

    $("#srType_add").change(function () {
        var srType = $("#srType_add").val();
        if ("0" == srType || "1" == srType) {
            $("#No3").show();
            $("#No4").hide();
        } else {
            $("#No4").show();
            $("#No3").hide();
        }
    });

    $("#srType_update").change(function () {
        var srType = $("#srType_update").val();
        if ("0" == srType || "1" == srType) {
            $("#No33").show();
            $("#No44").hide();
        } else {
            $("#No44").show();
            $("#No33").hide();
        }
    });
});

/**
 * 展示安全审计规则列表框
 */
function findRules() {
    var sdType = $("#sdType").val();
    var srType = $("#srType").val();
    if (sdType == 0) {
        sdType = null;
    }
    if (srType == 10) {
        srType = null;
    }
    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_seRules').bootstrapTable('destroy');
    $("#tb_seRules").bootstrapTable({
        url: './seRules/showRules',
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
                sdType: encodeURI(sdType),
                sdType1: sdType,
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
        uniqueId: 'srId',
        cardView: false,
        detailView: false,
        columns: [
            /*{field: 'state', checkbox: true, align: 'center', valign: 'middle'},*/
            {field: 'srId', title: 'id', visible: false, valign: 'middle'},
            {field: 'sdType', title: 'USB设备类型', align: 'center'},
            {field: 'srType', title: '终端IP', align: 'center'},
            {
                field: 'srDate', title: '接入日期', align: 'center',
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
