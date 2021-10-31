$("button").click(function () {
    var para = getUserRegisterInfo()

    $.ajax({
        //跳转 url
        url: "/register/check",
        type: "Post",
        data: para,
        datatype: "HTML",
        success: function (data) {
            eval(data);
        }
    })
});

function getCurrentTabIndex() {
    var $tabs = $('#register_tabs').children('li').children('a');
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

function getUserRegisterInfo() {
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
        registerWay: way,
        //用户登录账号
        userNum: accounting,
        userPw: password,
    };
    return para;
}
