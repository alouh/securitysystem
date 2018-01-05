/**
 * 退出登陆
 */
function logout() {

    var userName = getCookie("userName");

    $.ajax({
        url: './landManage/logout',
        type: 'post',
        data: {'userName': userName},
        dataType: 'json',
        success: function (result) {
            if (result) {
               window.location.href="/securitysystem/";
            }
        }
    });
}