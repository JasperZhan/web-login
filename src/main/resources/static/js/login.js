// 点击登录按钮
$("button").click(function () {
    login();
});

// 判断用户登录方式
// 0：手机登录
// 1：邮箱登录
// 2：密码登录
function getCurrentTabIndex() {
    var $tabs = $('#login_tabs').children('li').children('a');
    var i = 0;

    $tabs.each(function () {
        var $tab = $(this);
        if ($tab.hasClass('active')) {
            return false;
        } else {
            i++;
        }
    });
    return i;
}

// 获取用户登录信息
// 根据用户登录方式
// 获取手机号，密码
// 或者
// 获取邮箱号，密码
function getUserLoginInfo() {
    var way = getCurrentTabIndex()
    var accounting
    var password
    switch (way) {
        case 0:
            accounting = $("#user_tel").val()
            password = $('#tel_pw').val()
            break;
        case 1:
            accounting = $('#user_mail').val()
            password = $('#mail_pw').val()
            break;
        case 2:
            break;
    }
    var para = {
        //登录方式
        loginWay: way,
        //用户登录账号
        userNum: accounting,
        userPw: password,
        isSavePw: $("input[type='checkbox']").is(':checked')
    };
    return para;
}


//设置cookie
function setCookie(loginWay) {
    var date = new Date();
    // 设置cookie过期时间
    date.setTime(date.getTime() + 60 * 1000);
    if (loginWay == 0) {
        $.cookie("loginWay", 0, {expires: date, path: '/'});
        $.cookie("userNum", $("#user_tel").val(), {expires: date, path: '/'});
        $.cookie("userPw", $("#tel_pw").val(), {expires: date, path: '/'});
    } else if (loginWay == 1) {
        $.cookie("loginWay", 1, {expires: date, path: '/'})
        $.cookie("userNum", $("#user_mail").val(), {expires: date, path: '/'});
        $.cookie("userPw", $("#mail_pw").val(), {expires: date, path: '/'});
    }
}

//清除所有cookie函数
function clearAllCookie() {
    var date = new Date();
    date.setTime(date.getTime() - 10000);
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    console.log("需要删除的cookie名字：" + keys);
    if (keys) {
        for (var i = keys.length; i--;)
            document.cookie = keys[i] + "=undefined; expire=" + date.toGMTString() + "; path=/";
    }
}

//获取cookie
window.onload = function getCookie() {
    var userNum = $.cookie("userNum"); //获取cookie中的用户名
    var userPw = $.cookie("userPw"); //获取cookie中的登陆密码
    if (!userNum || userNum == "") {
        console.log("账户：" + userNum);
    } else {
        console.log($.cookie("loginWay"))
        if ($.cookie("loginWay") == 0) {
            $("#user_tel").val(userNum);
        } else if ($.cookie("loginWay") == 1) {
            $("#user_mail").val(userNum);
        }
    }
    if (!userPw || userPw == "") {
        console.log("密码：" + userPw);
    } else {
        //密码存在的话把密码填充到密码文本框
        //console.log("密码解密后："+$.base64.decode(pwd));
        // $("#mima").val($.base64.decode(pwd));
        //密码存在的话把“记住用户名和密码”复选框勾选住
        if ($.cookie("loginWay") == 0) {
            $("#tel_pw").val(userPw);
        } else if ($.cookie("loginWay") == 1) {
            $("#mail_pw").val(userPw);
        }
        $("input[type='checkbox']").attr("checked", "true");
    }

}

function login() {
    var para = getUserLoginInfo()

    //判断是否选中复选框，如果选中，添加cookie
    console.log("是否记住密码：" + para.isSavePw);
    if (para.isSavePw) {
        //添加cookie
        setCookie(para.loginWay);
    } else {
        clearAllCookie(para);
    }

    $.ajax({
        //跳转 url
        url: "/login/check",
        type: "Post",
        data: para,
        datatype: "HTML",
        success: function (data) {
            eval(data);
        }
    })
}


