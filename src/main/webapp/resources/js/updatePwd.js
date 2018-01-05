/**
 * 修改密码
 */
function updatePwd() {

    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var newPasswordAgain = $("#newPasswordAgain").val();

    var check = /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,}$/;

    var pwd = getCookie("passWord");
    var userId = getCookie("userId");

    if ("" == oldPassword || null == oldPassword || "" == newPassword ||
        null == newPassword || "" == newPasswordAgain || null == newPasswordAgain) {
        $("#messageText").text("请输入密码！");
        $("#message").modal("show");
        return false;
    }

    // 原始密码校验
    if (pwd != hex_md5(oldPassword)) {
        $("#messageText").text("原始密码与登陆密码不一致，请确认！");
        $("#message").modal("show");
        return false;
    }


    // 两次输入的密码校验
    if (newPassword != newPasswordAgain) {
        $("#messageText").text("新密码两次输入的不一致，请确认！");
        $("#message").modal("show");
        return false;
    }
    if (newPasswordAgain == oldPassword) {
        $("#messageText").text("新密码不能和原密码相同，请确认！");
        $("#message").modal("show");
        return false;
    }

    // 密码强度校验
    if (!check.test(newPassword)) {
        $("#messageText").text("新密码需要由8位以上大写字母、小写字母、数字、特殊字符中三种或三种以上的组合，请重新输入！");
        $("#message").modal("show");
        return false;
    }
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ9s1qlOyv9qpuaTqauW6fUftzE50rVk3yVPZwv1aO1Ch/XSEz76xCwkyvqpaqceRXrPpdBmO5+ruJ+I8osOHo7L5GWEOcMOO+8izp9hXKBBrmRMD4Egpn00k9DhVIEKp/vyddZPS/doxB8onhN6poTJDLdFLFVEicMf52caN9GQIDAQAB");
    encrypt.setPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIn2zWqU7K/2qm5pOpq5bp9R+3MTnStWTfJU9nC/Vo7UKH9dITPvrELCTK+qlqpx5Fes+l0GY7n6u4n4jyiw4ejsvkZYQ5ww477yLOn2FcoEGuZEwPgSCmfTST0OFUgQqn+/J11k9L92jEHyieE3qmhMkMt0UsVUSJwx/nZxo30ZAgMBAAECgYBD3YHigeuEC4R+14iaf8jo2j0kuGtB3Cxvnlez0otTqw1YyYkBsU49cLKkXvfKVEgM0Ow/QltgKvSBxCE31PrrDka5TygVMqqA/IM7NrDvjUcGLjyoeNmLA8660fWcDxUTlAGN5kxIvUATayVwKVflpWPWu0FPKsWrZustnEo+4QJBAMCmYsWqAKWYMVRXFP3/XGRfio8DV793TOckyBSN9eh8UhgoZyT3u7oeHmDJEwm4aNMHlg1Pcdc6tNsvi1FRCiUCQQC3VNzfF4xOtUgX7vWPL8YVljLuXmy12iVYmg6ofu9l31nwM9FLQ1TRFglvF5LWrIXTQb07PgGd5DJMAQWGsqLlAkAPE7Z9M73TN+L8b8hDzJ1leZi1cpSGdoa9PEKwYR/SrxAZtefEm+LEQSEtf+8OfrEtetWCeyo0pvKKiOEFXytFAkEAgynL/DC0yXsZYUYtmYvshHU5ayFTVagFICbYZeSrEo+BoUDxdI9vl0fU6A5NmBlGhaZ65G+waG5jLc1tTrlvoQJAXBEoPcBNAosiZHQfYBwHqU6mJ9/ZacJh3MtJzGGebfEwJgtln5b154iANqNWXpySBLvkK+Boq7FYRiD83pqmUg==");
    $.ajax({
        url: './landManage/updatePwd',
        type: 'post',
        data: {'newPassword': encrypt.encrypt(hex_md5(newPassword) + newPassword), 'userId': userId},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                $("#password_update").modal("hide");
                addCookie("passWord", hex_md5(newPassword));
                $("#msgText").text("密码修改成功");
                $("#msg").modal("show");
                return;
            } else {
                $("#messageText").text(result.msg);
                $("#message").modal("show");
                return false;
            }
        }
    });
}

function returnIndex() {
    location.href = "index"
}
