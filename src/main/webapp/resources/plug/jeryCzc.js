function pop(title, text, time) {

    if (title == undefined)
        title = "消息提示";

    if (text == undefined)
        text = "修改成功";

    if (time == undefined)
        time = 2000;


    $("#popHead span").html(title);
    $("#popTxt").html(text);

    $("#pop").show(100, "linear", function () {
        setTimeout("closePop()", time);
    });
}

function closePop() {
    $("#pop").hide();
}