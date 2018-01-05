$(function () {
    findAll();
});
function findAll() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var sdType = $("#sdType").val();
    if (new Date(startDate).getTime() > new Date().getTime()) {
        $("#messageText").text("开始时间大于当前时间");
        $("#startDate").val("");
        $("#message").modal("show");
        return false;
    }
    if (endDate < startDate) {
        $("#messageText").text("结束时间小于开始时间");
        $("#message").modal("show");
        $("#startDate").val("");
        $("#endDate").val("");
        return false;
    }
    findStatisticsView(startDate, endDate, sdType);
    findStatistics(startDate, endDate, sdType);
}
/**
 * 异常趋势图
 */
function findStatisticsView(startDate, endDate, sdType) {
    $.post('./seStatistics/showStatisticsView', {
        'startDate': encodeURI(startDate),
        'endDate': encodeURI(endDate),
        'sdType': encodeURI(sdType),
        'sdType1': sdType
    }, function (result) {
        var yData1 = [];
        for (var i = 0; i < result[0].length; i++) {
            yData1.push(result[0][i].ssCount)
        }
        var yData2 = [];
        for (var i = 0; i < result[1].length; i++) {
            yData2.push(result[1][i].ssCount)
        }
        var yData3 = [];
        for (var i = 0; i < result[2].length; i++) {
            yData3.push(result[2][i].ssCount)
        }
        var yData4 = [];
        for (var i = 0; i < result[3].length; i++) {
            yData4.push(result[3][i].ssCount)
        }

        var xData = [];
        for (var i = 0; i < result[0].length; i++) {
            xData.push(get_Date(result[0][i].ssDate))
        }
        var xDataSize = xData.length;
        if (xDataSize >= 29) {
            var chart = new Highcharts.Chart('myChart', {
                title: {
                    text: '异常趋势图',
                    x: -20
                },
                subtitle: {
                    text: '终端/主机设备安全设计统计',
                    x: -20
                },
                xAxis: {
                    categories: xData,
                    min: 0, //别忘了这里
                    max: 30,
                    labels: {
                        x: 45,//调节x偏移
                        //y:-35,//调节y偏移
                        rotation: -25//调节倾斜角度偏移
                    }
                },
                yAxis: {
                    title: {
                        text: '异常数量'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: []
            });
        } else {
            var chart = new Highcharts.Chart('myChart', {
                title: {
                    text: '异常趋势图',
                    x: -20
                },
                subtitle: {
                    text: '终端/主机设备安全设计统计',
                    x: -20
                },
                xAxis: {
                    categories: xData,
                    min: 0, //别忘了这里
                    max: xDataSize - 1,
                    labels: {
                        x: 45,//调节x偏移
                        //y:-35,//调节y偏移
                        rotation: -25//调节倾斜角度偏移
                    }
                },
                scrollbar: {
                    enabled: true //是否产生滚动条
                },
                yAxis: {
                    title: {
                        text: '异常数量'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: []
            });
        }
        chart.xAxis[0].categories = xData;
        var series = chart.addSeries({
            name: 'TCP端口',
            data: yData1
        }, true);

        var series = chart.addSeries({
            name: 'UDP端口',
            data: yData2
        }, true);
        var series = chart.addSeries({
            name: '软件',
            data: yData3
        }, true);
        var series = chart.addSeries({
            name: '漏洞/补丁',
            data: yData4
        }, true);

    });

}

/**
 *表格展示
 */
function findStatistics(startDate, endDate, sdType) {
    var heightTable = document.documentElement.clientHeight - 250;
    $('#tb_seStatistics').bootstrapTable('destroy');
    $("#tb_seStatistics").bootstrapTable({
        url: './seStatistics/showStatistics',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded", // POST请求需要 编码
        //toolbar: '#toolbar',
        striped: false, // 是否显示行间隔色
        cache: false,
        height: heightTable,
        pagination: true,
        sortOrder: 'desc',
        queryParams: function (params) {
            return {
                pageindex: this.pageNumber,
                pageSize: params.limit,
                'startDate': encodeURI(startDate),
                'endDate': encodeURI(endDate),
                'sdType': encodeURI(sdType),
                'sdType1': sdType
            }
        },
        silent: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [20, 50, 200],
        strictSearch: true,
        clickToSelect: true,
        uniqueId: 'ssId',
        cardView: false,
        detailView: false,
        columns: [
            {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'ssId', title: '设备id', visible: false, valign: 'middle'},
            {
                field: 'scDate', title: '日期', align: 'center',
                formatter: function (value) {
                    return get_Date(value);
                }
            },
            {
                field: 'tcpCount', title: 'TCP', align: 'center',
                formatter: function (value, row, index) {
                    if (value == null || value == undefined || value == 0) {
                        return "0";
                    } else {
                        return '<a style="color:blue;" href="#" onclick="showModel(\'' + get_Date(row.scDate) + '\',\'' + sdType + '\',' + 0 + ')">' + (value) + '</a>';
                    }
                }
            },
            {
                field: 'udpCount', title: 'UDP', align: 'center',
                formatter: function (value, row, index) {
                    if (value == null || value == undefined || value == 0) {
                        return "0";
                    } else {
                        return '<a style="color:blue;" href="#" onclick="showModel(\'' + get_Date(row.scDate) + '\',\'' + sdType + '\',' + 1 + ')">' + (value) + '</a>';
                    }
                }
            },
            {
                field: 'sofeWareCount', title: '软件', align: 'center',
                formatter: function (value, row, index) {
                    if (value == null || value == undefined || value == 0) {
                        return "0";
                    } else {
                        return '<a style="color:blue;" href="#" onclick="showModel(\'' + get_Date(row.scDate) + '\',\'' + sdType + '\',' + 2 + ')">' + (value) + '</a>';
                    }
                }
            },
            {
                field: 'loopholeCount', title: '漏洞/补丁', align: 'center',
                formatter: function (value, row, index) {
                    if (value == null || value == undefined || value == 0) {
                        return "0";
                    } else {
                        return '<a style="color:blue;" href="#" onclick="showModel(\'' + get_Date(row.scDate) + '\',\'' + sdType + '\',' + 3 + ')">' + (value) + '</a>';
                    }
                }
            }
        ]
    });
}

function showModel(scDate, sdType, srType) {
    $("#detailed_information").modal("show");
    $("#scDate").val(scDate);
    $("#sdType1").val(sdType);
    $("#srType").val(srType);
    showDetailMsg();
}
function showDetailMsg() {
    var scDate=$("#scDate").val();
    var sdType=$("#sdType1").val();
    var srType=$("#srType").val();
    var sdIp = $("#sdIp").val().trim();
    var srRname = $("#srRname").val().trim();
  //  $("#detailed_information").modal("show");
    var heightTable = document.documentElement.clientHeight - 200;
    $('#tb_detailStatistics').bootstrapTable('destroy');
    $("#tb_detailStatistics").bootstrapTable({
        url: './seStatistics/showDetailStatistics',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded", // POST请求需要 编码
       // toolbar: '#toolbar',
        striped: false, // 是否显示行间隔色
        cache: false,
        height: heightTable,
        pagination: true,
        sortOrder: 'desc',
        queryParams: function (params) {
            return {
                pageindex: this.pageNumber,
                pageSize: params.limit,
                scDate: encodeURI(scDate),
                sdType: encodeURI(sdType),
                sdType1: sdType,
                srType: srType,
                sdIp: encodeURI(sdIp),
                srRname: encodeURI(srRname)
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
            {field: 'sdName', title: '设备名称', align: 'center',width:150},
            {field: 'sdType', title: '设备类型', align: 'center',width:95},
            {field: 'sdOs', title: '操作系统', align: 'center',width:120},
            {field: 'sdOsType', title: '系统版本', align: 'center',width:95},
            {field: 'sdUser', title: '使用人', align: 'center',width:110},
            {field: 'sdIp', title: 'IP地址', align: 'center',width:105},
            {field: 'srType', title: '审计类型', align: 'center',width:95,
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
                }},
            {field: 'srRname', title: '审计内容', align: 'center',width:100},

            {field: 'srRules', title: '审计规则', align: 'center',width:95,
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
            {field: 'stReason', title: '异常原因', align: 'center',width:150},
            {
                field: 'stDate', title: '审计日期', align: 'center',width:110,
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