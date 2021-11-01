$("#register").click(function () {
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

var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
$('#getVc').on('click', function () {
    curCount = count;
    //设置button效果，开始计时
    $("#getVc").attr("disabled", "disabled");
    $("#getVc").addClass('disable');
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
});

function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#getVc").removeAttr("disabled");//启用按钮
        $("#getVc").removeClass('disable');
        $("#getVc").text("重新获取");
    } else {
        curCount--;
        $("#getVc").text("" + curCount + "s后获取");
    }
}