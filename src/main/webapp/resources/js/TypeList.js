$(function () {
    findTypeList();
});

/**
 * 台账管理
 */
function findTypeList() {
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
                pageIndex: this.pageNumber,
                pageSize: params.limit
            }
        },
        silent: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [20, 50, 200],
        strictSearch: true,
        clickToSelect: true,
        uniqueId: 'tl_Id',
        cardView: false,
        detailView: false,
        columns: [
            {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'tl_Id', title: '序号', visible: false, valign: 'middle'},
            {field: 'tl_OsName', title: '操作系统', align: 'center', width: '150px'},
            {field: 'tl_Type', title: 'USB设备类型', align: 'center', width: '150px'},
            {field: 'tl_Path', title: '注册表路径', align: 'center', width: '500px'},
            {field: 'tl_Allow', title: '禁用/允许', align: 'center', width: '120px'},
            {
                field: 'tl_Date', title: '维护日期', align: 'center', width: '120px',
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

    var tl_OsName = $("#tl_OsName_add").val();
    var tl_Type = $("#tl_Type_add").val();
    var tl_Path = $("#tl_Path_add").val();
    var tl_Allow = $("#tl_Allow_add").val();

    $.ajax({
        url: './typeList/addType',
        type: 'post',
        data: {
            tl_OsName:tl_OsName,
            tl_Type:tl_Type,
            tl_Path:tl_Path,
            tl_Allow:tl_Allow
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
 * 修改USB设备类型
 */
function updateTypeList() {

    var rows = $("#tb_typeList").bootstrapTable('getSelections');
    if (rows.length !== 1) {
        $("#messageText").text("请选择一条数据进行修改！");
        $("#message").modal("show");
        return false;
    }
    getEditData();
    $("#typeList_update").modal("show");
}

function getEditData() {

    var rows = $("#tb_typeList").bootstrapTable('getSelections');
    var id = rows[0].tl_Id;

    $.ajax({
        url:'./typeList/getEditData',
        type:'post',
        data:{
            selectedId:id
        },
        dataType:'json',
        success:function (result) {
            if (result.success){
                document.getElementById("tl_Type_update").value = result.type;
                document.getElementById("tl_Path_update").value = result.path;
                $("#tl_Allow_update").val(result.allow);
                $("#tl_OsName_update").val(result.osName);
            }else{
                $("#messageText").text("获取编辑信息失败");
                $("#message").modal("show");
            }
        }
    })
}

function update() {

    var rows = $("#tb_typeList").bootstrapTable('getSelections');
    var id = rows[0].tl_Id;
    var tl_OsName = $("#tl_OsName_update").val();
    var tl_Type = $("#tl_Type_update").val();
    var tl_Path = $("#tl_Path_update").val();
    var tl_Allow = $("#tl_Allow_update").val();

    $.ajax({
        url: './typeList/updateTypeList',
        type: 'post',
        data: {
            selectedId:id,
            tl_OsName:tl_OsName,
            tl_Type:tl_Type,
            tl_Path:tl_Path,
            tl_Allow:tl_Allow

        },
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#updateModels").modal("hide");
                $("#typeList_update").modal("hide");
                pop("消息提示","修改成功","2000");
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
        ids += "," + rows[i].tl_Id;
    }
    $.ajax({
        url: './typeList/delTypeByIds',
        type: 'post',
        data: {'tl_Ids': ids.substring(1)},
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