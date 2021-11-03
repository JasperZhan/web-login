$("#logout").click(function () {
    $.ajax({
        //跳转 url
        url: "/logout",
        type: "Post",
        data: null,
        datatype: "HTML",
        success: function (data) {
            eval(data);
        }
    })
});

window.onload = function () {
    var btn = document.getElementById("confirm")
    let tel = document.getElementById("tel")
    console.log(tel)
    console.log(tel.value)
    let email = document.getElementById("email")
    console.log(email)
    if (tel.value === "" || tel.value == null) {
        btn.innerHTML="添加手机号";
        $('#confirm').on('click', function () {
            $.ajax({
                //跳转 url
                url: "/addTel",
                type: "Post",
                data: null,
                datatype: "HTML",
                success: function (data) {
                    eval(data);
                }
            })
        })
    } else if (email.value === "" || email.value == null) {
        btn.innerHTML="添加邮箱号";
        $('#confirm').on('click', function () {
            $.ajax({
                //跳转 url
                url: "/addMail",
                type: "Post",
                data: null,
                datatype: "HTML",
                success: function (data) {
                    eval(data);
                }
            })
        })
    } else {
        btn.innerHTML="信息完整";
        $('#confirm').on('click', function () {
            alert("当前信息已完整，无需修改")
        })
    }
}