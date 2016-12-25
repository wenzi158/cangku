
$(document).bind("keydown", function (e) {
    if (e.keyCode == 13) {
        console.log("enter");
        return login();
    }
});


function login() {
    var hash = hex_md5($("#password").val());
    var user = $("#username").val();
    var userPwd = hash;
    if (user == "") {
        alert("用户名不可为空");
        return false;
    }

    if (userPwd == "" || userPwd.length < 6) {
        alert("密码长度大于六位");
        return false;
    }
    var tmp = {"username": user, "password": userPwd};


    $.ajax({
        url: configUrl.urlRelease + "login",
        dataType: "json",
        type: "post",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(tmp),
        success: function (data) {
            //var auth = data.authority;
            if (data.msg == "Success") {
                console.log("登录成功");
                //addCookie("oAuthority", auth, "/");
                location.href = "../html/register.html";

            }

        },
        error: function (data) {
            var errorMsg = JSON.parse(data.responseText);
            console.log(errorMsg);
            alert(errorMsg.msg);
        }
    })
}