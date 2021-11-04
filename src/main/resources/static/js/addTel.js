$("#changeInfo").click(function () {
    var para = {
        tel: $("#user_tel").val(),
        code: $("#vc").val()
    }
    if (para.code == null || para.code ==="") {
        alert("请输入验证码")
    } else {
        $.ajax({
            //跳转 url
            url: "/addTel/check",
            type: "Post",
            data: para,
            datatype: "HTML",
            success: function (data) {
                eval(data);
            }
        })
    }

});

var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
$('#getVc').on('click', function () {
    var data = {
        tel: $("#user_tel").val()
    }
    if (data.tel == null || data.tel === "") {
        alert("请先输入手机号噢");
        return;
    }
    $.ajax({
        //跳转 url
        url: "/addTel/code",
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