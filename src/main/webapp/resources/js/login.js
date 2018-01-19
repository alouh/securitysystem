$(function () {
    $('#btn').click(function () {
        login();
    });
});
/**
 * 登录
 * @returns {boolean}
 */
function login() {
    var userName = $("#userName").val().trim();
    var passWord = $("#pwd").val().trim();
    if ("" === userName || null == userName || "" === passWord || null == passWord) {
        //登录失败报错显示
        $(".login-error").show();
        //input获取焦点，登录失败报错消失
        $('#userName,#pwd').focus(function () {
            $(".login-error").hide();
        });
        return false;
    }
    var passwords = hex_md5(passWord) + passWord;
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ9s1qlOyv9qpuaTqauW6fUftzE50rVk3yVPZwv1aO1Ch/XSEz76xCwkyvqpaqceRXrPpdBmO5+ruJ+I8osOHo7L5GWEOcMOO+8izp9hXKBBrmRMD4Egpn00k9DhVIEKp/vyddZPS/doxB8onhN6poTJDLdFLFVEicMf52caN9GQIDAQAB");
    encrypt.setPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIn2zWqU7K/2qm5pOpq5bp9R+3MTnStWTfJU9nC/Vo7UKH9dITPvrELCTK+qlqpx5Fes+l0GY7n6u4n4jyiw4ejsvkZYQ5ww477yLOn2FcoEGuZEwPgSCmfTST0OFUgQqn+/J11k9L92jEHyieE3qmhMkMt0UsVUSJwx/nZxo30ZAgMBAAECgYBD3YHigeuEC4R+14iaf8jo2j0kuGtB3Cxvnlez0otTqw1YyYkBsU49cLKkXvfKVEgM0Ow/QltgKvSBxCE31PrrDka5TygVMqqA/IM7NrDvjUcGLjyoeNmLA8660fWcDxUTlAGN5kxIvUATayVwKVflpWPWu0FPKsWrZustnEo+4QJBAMCmYsWqAKWYMVRXFP3/XGRfio8DV793TOckyBSN9eh8UhgoZyT3u7oeHmDJEwm4aNMHlg1Pcdc6tNsvi1FRCiUCQQC3VNzfF4xOtUgX7vWPL8YVljLuXmy12iVYmg6ofu9l31nwM9FLQ1TRFglvF5LWrIXTQb07PgGd5DJMAQWGsqLlAkAPE7Z9M73TN+L8b8hDzJ1leZi1cpSGdoa9PEKwYR/SrxAZtefEm+LEQSEtf+8OfrEtetWCeyo0pvKKiOEFXytFAkEAgynL/DC0yXsZYUYtmYvshHU5ayFTVagFICbYZeSrEo+BoUDxdI9vl0fU6A5NmBlGhaZ65G+waG5jLc1tTrlvoQJAXBEoPcBNAosiZHQfYBwHqU6mJ9/ZacJh3MtJzGGebfEwJgtln5b154iANqNWXpySBLvkK+Boq7FYRiD83pqmUg==");
    var password= encrypt.encrypt(passwords);
    $.ajax({
        url: './landManage/userLogin',
        type: 'post',
        data: {'userName': userName, 'password': password},
        dataType: 'json',
        success: function (result) {
            if (result.success) {
                addCookie("userName", result.userName);
                addCookie("passWord", hex_md5(passWord));
                addCookie("userId", result.userId);
                // 密码强度校验
                location.href = "home";

            } else {
                //登录失败报错显示
                $(".login-error").show();
                //input获取焦点，登录失败报错消失
                $('#userName,#pwd').focus(function () {
                    $(".login-error").hide();
                });
            }
        }
    });
}


