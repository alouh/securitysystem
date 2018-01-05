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
            {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'srId', title: 'id', visible: false, valign: 'middle'},
            {field: 'sdType', title: '设备类型', align: 'center'},
            {field: 'sdOs', title: '操作系统', align: 'center'},
            {field: 'sdOstype', title: '系统版本', align: 'center'},
            {
                field: 'srType', title: '审计类型', align: 'center',
                formatter: function (value) {
                    if (value == 0) {
                        return "TCP端口";
                    } else if (value == 1) {
                        return "UDP端口";
                    } else if (value == 2) {
                        return "软件";
                    } else if (value == 3) {
                        return "漏洞/补丁";
                    } else {
                        return value;
                    }
                }
            },
            {field: 'srRname', title: '审计内容', align: 'center'},
            {
                field: 'srRules', title: '审计规则', align: 'center',
                formatter: function (value) {
                    if (value == 1) {
                        return "必关闭";
                    } else if (value == 2) {
                        return "必安装";
                    } else {
                        return value;
                    }
                }
            },
            {
                field: 'srDate', title: '创建日期', align: 'center',
                formatter: function (value) {
                    return get_Date(value);
                }
            }
        ]
    });
}
/**
 * 修改
 */
function seRulesUpdate() {
    var rows = $("#tb_seRules").bootstrapTable('getSelections');
    if (rows.length != 1) {
        $("#messageText").text("请选择一条数据进行修改");
        $("#message").modal("show");
        return false;
    }
    var sdType = rows[0].sdType;
    $('#sdType_update').selectpicker("val", sdType);
        $('#sdOs_update').selectpicker("val", rows[0].sdOs);
        $('#sdOsType_update').selectpicker("val", rows[0].sdOstype);
    var srType = rows[0].srType;
    $('#srType_update').selectpicker("val", srType);
    if ("0" == srType || "1" == srType) {
        $("#No33").show();
        $("#No44").hide();
    } else {
        $("#No44").show();
        $("#No33").hide();
    }
    var srRname = rows[0].srRname;
    $("#srRname_update").val(srRname);
    $('#seRules_update').modal('show');
}
function updateRules() {
    var rows = $("#tb_seRules").bootstrapTable('getSelections');
    var srId = rows[0].srId;
    var sdType = $("#sdType_update").val();
    var sdOs = $("#sdOs_update").val();
    var sdOsType = $("#sdOsType_update").val();

    var srType = $("#srType_update").val();
    var srRules;
    if ("0" == srType || "1" == srType) {
        srRules = $("#srRules_update1").val();
    } else {
        srRules = $("#srRules_update2").val();
    }

    var srRname = $("#srRname_update").val();

    if (null == srRname || "" == srRname) {
        $("#messageText").text("请填完数据再提交");
        $("#message").modal("show");
        return false;
    }

    $.ajax({
        url: './seRules/updateRules',
        type: 'post',
        data: {
            'srId': srId,
            'sdType': sdType,
            'sdOsType': sdOsType,
            'sdOs': sdOs,
            'srType': srType,
            'srRules': srRules,
            'srRname': srRname
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#seRules_update").modal("hide");
                pop("消息提示", "修改成功", "2000");
                findRules();
            } else {
                $("#messageText").text(result.msg);
                pop("消息提示", "修改失败", "2000");
                $("#message").modal("show");
                return false;
            }
        }
    });
}
/**
 * 批量刪除
 */
function delSeRules() {
    var rows = $("#tb_seRules").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $("#messageText").text("请选择一条数据进行删除！");
        $("#message").modal("show");
        return false;
    }
    $("#delcfmModels").modal("show");
}
function dels() {
    var rows = $("#tb_seRules").bootstrapTable('getSelections');
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        ids += "," + rows[i].srId;
    }
    $.ajax({
        url: './seRules/delRulesByIds',
        type: 'post',
        data: {'srIds': ids.substring(1)},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#delcfmModels").modal("hide");
                pop("消息提示", "删除成功", "2000");
                findRules();
            } else {
                $("#messageText").text(result.msg);
                pop("消息提示", "删除失败", "2000");
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
 *新增安全审计规则
 */
function addRules() {
    var sdType = $("#sdType_add").val();
    var sdOs = $("#sdOs_add").val();
    var sdOsType = $("#sdOsType_add").val();
    var srType = $("#srType_add").val();
    var srRules;
    if ("0" == srType || "1" == srType) {
        srRules = $("#srRules_add1").val();
    } else {
        srRules = $("#srRules_add2").val();
    }

    var srRname = $("#srRname_add").val();

    if (null == srRname || "" == srRname) {
        $("#messageText").text("请填完数据再提交");
        $("#message").modal("show");
        return false;
    }

    $.ajax({
        url: './seRules/addRules',
        type: 'post',
        data: {
            'sdType': sdType,
            'sdOsType': sdOsType,
            'sdOs': sdOs,
            'srType': srType,
            'srRules': srRules,
            'srRname': srRname
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#seRules_add").modal("hide");
                pop("消息提示", "新增成功", "2000");
                findRules();
            } else {
                $("#messageText").text(result.msg);
                pop("消息提示", "新增失败", "2000");
                $("#message").modal("show");
                return false;
            }
        }
    });

}
