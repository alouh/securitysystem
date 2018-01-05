$(function () {
    findSeDevice();
});

$(function () {
    $("#sdType_add").change(function () {
        var sdType = $("#sdType_add").val();
        if ("网络设备" == sdType) {
            $("#No1").hide();
            $("#No2").hide();
            $("#No3").hide();
        } else {
            $("#No1").show();
            $("#No2").show();
            $("#No3").show();
        }
    });
});
/**
 * 台账管理
 */
function findSeDevice() {
    var sdIp = $("#sdIp").val().trim();
    var sdType = $("#sdType").val();
    var sdUser = $("#sdUser").val().trim();
    if (sdType == 0) {
        sdType = null;
    }
    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_seDevice').bootstrapTable('destroy');
    $("#tb_seDevice").bootstrapTable({
        url: './seDevice/showSeDevice',
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
            {field: 'sdId', title: '序号', align: 'center',width:'120px'},
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
function addDevice() {
    //var sdName = $("#sdName_add").val().trim();
    var sdType = $("#sdType_add").val();
    /*var sdOs = null;
    var sdOsType = null;
    var sdMac = null;
    if (sdType != "网络设备") {
        sdOs = $("#sdOs_add").val();
        sdOsType = $("#sdOsType_add").val();
        sdMac = $("#sdMac_add").val().trim()
    }*/
    /*var sdDept = $("#sdDept_add").val().trim();
    var sdUser = $("#sdUser_add").val().trim();
    var sdIp = $("#sdIp_add").val().trim();

    if (null == sdName || "" == sdName || "" == sdDept || null == sdName || "" == sdUser || null == sdUser || "" == sdIp || null == sdIp) {
        $("#messageText").text("请填完数据再提交");
        $("#message").modal("show");
        return false;
    }
    if (!(isValidIP(sdIp))) {
        $("#messageText").text('IP格式不正确');
        $("#message").modal("show");
        return false;
    }*/

    $.ajax({
        url: './seDevice/addDevice',
        type: 'post',
        data: {
            //'sdName': sdName,
            'sdType': sdType,
            /*'sdOs': sdOs,
            'sdOsType': sdOsType,
            'sdMac': sdMac,
            'sdDept': sdDept,
            'sdUser': sdUser,
            'sdIp': sdIp*/
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#seDevice_add").modal("hide");
                pop("消息提示","新增成功","2000");
                findSeDevice();
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
    var rows = $("#tb_seDevice").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $("#messageText").text("请选择一条数据进行删除！");
        $("#message").modal("show");
        return false;
    }
    $("#delcfmModels").modal("show");
}
function dels() {
    var rows = $("#tb_seDevice").bootstrapTable('getSelections');
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        ids += "," + rows[i].sdId;
    }
    $.ajax({
        url: './seDevice/delDeviceByIds',
        type: 'post',
        data: {'sdIds': ids.substring(1)},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#delcfmModels").modal("hide");
                pop("消息提示","删除成功","2000");
                findSeDevice();
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
    window.location.href = "seInstalled?id=" + sdid;
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

/**
 * 判断是否为ip地址
 * @param ip
 * @returns {boolean}
 */
function isValidIP(ip) {
    var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    return reg.test(ip);
}