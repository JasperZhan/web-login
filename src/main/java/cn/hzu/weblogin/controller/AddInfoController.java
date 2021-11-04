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
public class AddInfoController {
    @Resource
    private SmsService smsService;

    @Resource
    private UserService userService;

    @RequestMapping("/addTel")
    public String addTel() {
        return "addTel";
    }

    @RequestMapping("/addMail")
    public String addMail() {
        return "addMail";
    }

    @ResponseBody
    @RequestMapping("/addTel/code")
    public String addTelCode(String tel, HttpSession session) {
        // 保存申请发送验证码时的手机号或邮箱号，作为修改信息时的验证
        session.setAttribute("tel", tel);

        // 保存发送验证码服务生成的验证码结果
        Code code = new Code();
        Result<Code> result = new Result<>();

        result = smsService.sendByPhone(tel);

        //发送验证码失败
        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        // 保存发送的验证码，以及发送验证码的时间
        session.setAttribute("code", result.getData().getCode());
        session.setAttribute("sendTime", String.valueOf(result.getData().getCurrentTime()));

        return "alert('验证码已发送，请注意查看')";
    }

    @ResponseBody
    @RequestMapping("/addMail/code")
    public String addMailCode(String mail, HttpSession session) {
        // 保存申请发送验证码时的手机号或邮箱号，作为注册时的验证
        session.setAttribute("mail", mail);

        // 保存发送验证码服务生成的验证码结果
        Code code = new Code();
        Result<Code> result = new Result<>();
        result = smsService.sendByEmail(mail);

        //发送验证码失败
        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        // 保存发送的验证码，以及发送验证码的时间
        session.setAttribute("code", result.getData().getCode());
        session.setAttribute("sendTime", String.valueOf(result.getData().getCurrentTime()));

        return "alert('验证码已发送，请注意查看')";
    }

    @ResponseBody
    @RequestMapping("/addTel/check")
    public String addTelCheck(String tel, String code, HttpSession session) {
        // 防止用户发送验证码后修改号码
        if (!tel.equals(session.getAttribute("tel").toString())) {
            return "alert('修改失败，手机号码与发送验证码的手机号不匹对')";
        }

        // 验证码与发送的验证码不一样
        if (!code.equals(session.getAttribute("code").toString())) {
            return "alert('验证码错误，请重新输入')";
        }

        long sendTime = Long.parseLong(session.getAttribute("sendTime").toString());
        if (!((System.currentTimeMillis() / 1000) - sendTime < 60)) {
            session.removeAttribute("code");
            return "alert('验证码已过期，请重新获取')";
        }

        User user = new User();
        Result<User> result = new Result<>();

        user.setTel(tel);
        user.setUserId((Integer) session.getAttribute("user_id"));
        result = userService.addInfoByTel(user);

        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        // 修改信息完成
        // 清楚session
        session.invalidate();
        return "location.href='/main'";
    }

    @ResponseBody
    @RequestMapping("/addMail/check")
    public String addMailCheck(String mail, String code, HttpSession session) {
        // 防止用户发送验证码后修改号码
        if (!mail.equals(session.getAttribute("mail").toString())) {
            return "alert('修改失败，邮箱号码与发送验证码的邮箱号不匹对')";
        }

        // 验证码与发送的验证码不一样
        if (!code.equals(session.getAttribute("code").toString())) {
            return "alert('验证码错误，请重新输入')";
        }

        long sendTime = Long.parseLong(session.getAttribute("sendTime").toString());
        if (!((System.currentTimeMillis() / 1000) - sendTime < 60)) {
            session.removeAttribute("code");
            return "alert('验证码已过期，请重新获取')";
        }

        User user = new User();
        Result<User> result = new Result<>();

        user.setEmail(mail);
        user.setUserId((Integer) session.getAttribute("user_id"));
        result = userService.addInfoByMail(user);

        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        // 修改信息完成
        // 清楚session
        session.invalidate();
        return "location.href='/main'";
    }
}
