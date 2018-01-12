$(function () {
    findTypeList();
});

/**
 * 台账管理
 */
function findTypeList() {
    var sdIp = $("#sdIp").val().trim();
    var sdType = $("#sdType").val();
    var sdUser = $("#sdUser").val().trim();
    if (sdType === 0) {
        sdType = null;
    }
    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_typeList').bootstrapTable('destroy');
    $("#tb_typeList").bootstrapTable({
        url: './typeList/showTypeList',
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
                sdIp: encodeURI(sdIp),
                sdType: encodeURI(sdType),
                sdType1: sdType,
                sdUser: encodeURI(sdUser)
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
            {field: 'sdId', title: '序号', visible: false, valign: 'middle'},
            {field: 'sdType', title: '设备类型', align: 'center',width:'120px'},
            {
                field: 'sdDate', title: '维护日期', align: 'center',width:'120px',
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
function addType() {
    var sdType = $("#sdType_add").val();

    $.ajax({
        url: './typeList/addDevice',
        type: 'post',
        data: {
            'sdType': sdType
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#typeList_add").modal("hide");
                pop("消息提示","新增成功","2000");
                findTypeList();
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
function delType() {
    var rows = $("#tb_typeList").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $("#messageText").text("请选择一条数据进行删除！");
        $("#message").modal("show");
        return false;
    }
    $("#delcfmModels").modal("show");
}
function dels() {
    var rows = $("#tb_typeList").bootstrapTable('getSelections');
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        ids += "," + rows[i].sdId;
    }
    $.ajax({
        url: './typeList/delDeviceByIds',
        type: 'post',
        data: {'sdIds': ids.substring(1)},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#delcfmModels").modal("hide");
                pop("消息提示","删除成功","2000");
                findTypeList();
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
 * 显示安装详情
 * @param sdid
 */
function detailview(sdid) {
    window.location.href = "sMPlatform?id=" + sdid;
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

/**
 * 判断是否为ip地址
 * @param ip
 * @returns {boolean}
 */
function isValidIP(ip) {
    var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    return reg.test(ip);
}