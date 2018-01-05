$(function () {
    findSeUser();
});
/**
 * 用户列表展示
 */
function findSeUser() {
    var heightTable = document.documentElement.clientHeight - 45;
    $('#tb_seUser').bootstrapTable('destroy');
    $("#tb_seUser").bootstrapTable({
        url: './userManger/showUser',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded", // POST请求需要 编码
        toolbar: '#toolbar',
        striped: false, // 是否显示行间隔色
        cache: false,
        //height: 896,
        height: heightTable,
        pagination: true,
        sortable: true,
        sortName: 'userId',
        sortOrder: 'desc',
        queryParams: function (params) {
            return {
                pageindex: this.pageNumber,
                pageSize: params.limit,
                sortName: this.sortName,
                sortOrder: this.sortOrder
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
            {field: 'userId', title: '用户id', visible: false, valign: 'middle'},
            {field: 'userName', title: '用户名', align: 'center'},
            {field: 'realName', title: '真实姓名', align: 'center'},
            /*   {
             field: 'sex', title: '性别', align: 'center',
             formatter: function (value) {
             if (value == 0) {
             return "男";
             } else if (value == 1) {
             return "女"
             } else {
             return value;
             }
             }
             },*/
            {
                field: 'creatTime', title: '创建时间', align: 'center',
                formatter: function (value) {
                    return get_Date(value);
                }
            }
        ]
    });
}
/**
 * 添加用戶
 * @returns {boolean}
 */
function addUser() {
    var userName = $("#userName_add").val().trim();
    var realName = $("#realName_add").val().trim();
    /*    var sex = $("#sex_add").val();*/
    if (null == userName || null == realName || "" == userName || "" == realName) {
        $("#messageText").text("请填完数据再提交");
        $("#message").modal("show");
        return false;
    }
    $.ajax({
        url: './userManger/addUser',
        type: 'post',
        data: {
            'userName': userName,
            'realName': realName,
            'sex': 0
        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#user_add").modal("hide");
                pop("消息提示", "新增成功", "2000");
                findSeUser();
            } else {
                $("#messageText").text(result.msg);
                pop("消息提示", "新增失败", "2000");
                $("#message").modal("show");
                return false;
            }
        }
    });


}
/**
 * 批量刪除
 */
function delSeUser() {
    var rows = $("#tb_seUser").bootstrapTable('getSelections');
    if (rows.length < 1) {
        $("#messageText").text("请选择一条数据进行删除！");
        $("#message").modal("show");
        return false;
    }
    $("#delcfmModels").modal("show");
}
function dels() {
    var rows = $("#tb_seUser").bootstrapTable('getSelections');
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        ids += "," + rows[i].userId
    }
    $.ajax({
        url: './userManger/delUserByIds',
        type: 'post',
        data: {'userIds': ids.substring(1)},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#delcfmModels").modal("hide");
                pop("消息提示", "删除成功", "2000");
                findSeUser();
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