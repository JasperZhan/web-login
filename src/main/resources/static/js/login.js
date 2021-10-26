$(//点击登录
  function () {
    $('button').click(function () {

      //登录方式
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
        //是否记住密码
        savePw: $("input[type='checkbox']").is(':checked')
      };

      //test
      console.log("登录方式：" + way);
      console.log("号码：" + para.userNum);
      console.log("密码：" + para.userPw);
      console.log("是否保存密码：" + para.savePw);

      // ajax
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
    });

  },
)

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
