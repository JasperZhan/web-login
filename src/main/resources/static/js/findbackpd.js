$("#findBack").click(function () {
    var para = getUserFindBackInfo()

    $.ajax({
        //跳转 url
        url: "/findBackPassword/check",
        type: "Post",
        data: para,
        datatype: "HTML",
        success: function (data) {
            eval(data);
        }
    })
});

function getCurrentTabIndex() {
    var $tabs = $('#findBack_tabs').children('li').children('a');
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

function getUserFindBackInfo() {
    var way = getCurrentTabIndex()
    var accounting
    var password
    var password_second
    var code = $("#vc").val()
    switch (way) {
        case 0:
            accounting = $("#user_tel").val()
            password = $('#tel_pw').val()
            password_second = $('#tel_pw1').val()
            break;
        case 1:
            accounting = $('#user_mail').val()
            password = $('#mail_pw').val()
            password_second = $('#mail_pw1').val()
            break;
        case 2:
            break;
    }
    var para = {
        //登录方式
        findBackWay: way,
        //用户登录账号
        userNum: accounting,
        pwd: password,
        pwd_second: password_second,
        code: code
    };
    return para;
}

var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
$('#getVc').on('click', function () {
    var para = getUserFindBackInfo();
    var data = {
        findBackWay: para.findBackWay,
        userNum: para.userNum,
    }
    if (data.userNum == null || data.userNum === "") {
        alert("请先输入手机号或邮箱号噢");
        return;
    }
    $.ajax({
        //跳转 url
        url: "/findBackPassword/code",
        type: "Post",
        data: data,
        datatype: "HTML",
        success: function (data) {
            eval(data);
        }
    })
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