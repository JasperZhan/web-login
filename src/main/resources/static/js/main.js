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