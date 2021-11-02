package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Code;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.SmsService;
import cn.hzu.weblogin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Resource
    UserService userService;

    @Resource
    SmsService smsService;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @RequestMapping("/register/code")
    public String registerCode(String registerWay, String userNum, HttpSession session) {

        int i = Integer.parseInt(registerWay);

        // 保存申请发送验证码时的手机号或邮箱号，作为注册时的验证
        session.setAttribute("registerWay", i);
        session.setAttribute("userNum", userNum);

        // 保存发送验证码服务生成的验证码结果
        Code code = new Code();
        Result<Code> result = new Result<>();

        switch (i) {
            case 0:
                result = smsService.sendByPhone(userNum);
                break;
            case 1:
                result = smsService.sendByEmail(userNum);
                break;
        }

        //发送验证码失败
        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        // 保存发送的验证码，以及发送验证码的时间
        session.setAttribute("code", result.getData().getCode());
        session.setAttribute("sendTime", String.valueOf(result.getData().getCurrentTime()));

        return "alert('验证码已发送，请注意查看')";
    }

    @ResponseBody
    @RequestMapping("/register/check")
    public String registerCheck(String registerWay, String userNum, String userPw, String code, HttpSession session) {

        int i = Integer.parseInt(registerWay);

        // 点击注册时 手机号/邮箱号 与发送验证码时填入的号码不一样
        // 防止用户发送验证码后修改号码
        if (!userNum.equals(session.getAttribute("userNum").toString())) {
            switch (i) {
                case 0:
                    return "alert('注册失败，手机号码与发送验证码的手机号不匹对')";
                case 1:
                    return "alert('注册失败，邮箱号码与发送验证码的邮箱号不匹对')";
            }
        }

        // 验证码与发送的验证码不一样
        if (!code.equals(session.getAttribute("code").toString())) {
            return "alert('验证码错误，请重新输入')";
        }

        //
        long sendTime = Long.parseLong(session.getAttribute("sendTime").toString());
        if (!((System.currentTimeMillis() / 1000) - sendTime < 60)) {
            session.removeAttribute("code");
            return "alert('验证码已过期，请重新获取')";
        }

        // 判断密码格式
        if (userPw == null)
            return "alert('密码不能为空')";
        if (!userPw.matches("^\\w{8,20}$"))
            return "alert('密码长度需在8-20位之间')";
        if (!userPw.matches(".*\\d+.*"))
            return "alert('密码需包含数字')";
        if (!userPw.matches(".*[a-zA-Z]+.*"))
            return "alert('密码需包含字母')";

        User user = new User();
        Result<User> result = new Result<>();

        // 根据用户输出的手机号或邮箱号进行注册
        switch (i) {
            case 0:
                user.setTel(userNum);
                user.setPassword(userPw);
                result = userService.registerByTel(user);
                break;
            case 1:
                user.setEmail(userNum);
                user.setPassword(userPw);
                result = userService.registerByMail(user);
                break;
        }

        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        // 注册完成
        // 清楚session
        // 反回登录页面
        session.invalidate();
        return "location.href='/login'";
    }
}
