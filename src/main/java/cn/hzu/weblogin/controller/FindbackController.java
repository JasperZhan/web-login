package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Code;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.SmsService;
import cn.hzu.weblogin.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @className: FindbackController
 * @description: 找回密码接口
 * @createDate: 2021/11/2
 */
@Controller
public class FindbackController {

    @Resource
    UserService userService;

    @Resource
    SmsService smsService;

    @RequestMapping("/findBackPassword")
    public String findBack() {
        return "findbackpd";
    }

    @ResponseBody
    @RequestMapping("/findBackPassword/code")
    public String registerCode(String findBackWay, String userNum, HttpSession session) {

        int i = Integer.parseInt(findBackWay);

        // 保存申请发送验证码时的手机号或邮箱号，作为找回密码时的验证
        session.setAttribute("findBackWay", i);
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
    @RequestMapping("/findBackPassword/check")
    public String findBackPwdByPhone(String findBackWay, String userNum, String pwd, String pwd_second, String code, HttpSession session) {

        int i = Integer.parseInt(findBackWay);

        String retStr;
        Result<User> result = null;
        User user = new User();

        if (!userNum.equals(session.getAttribute("userNum").toString())) {
            switch (i) {
                case 0:
                    return "alert('找回失败，手机号码与发送验证码的手机号不匹对')";
                case 1:
                    return "alert('找回失败，邮箱号码与发送验证码的邮箱号不匹对')";
            }
        }

        if (!pwd.equals(pwd_second)) {
            return "alert('两次密码输入不一致')";
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

        // 判断密码格式
        if (pwd == null)
            return "alert('密码不能为空')";
        if (!pwd.matches("^\\w{8,20}$"))
            return "alert('密码长度需在8-20位之间')";
        if (!pwd.matches(".*\\d+.*"))
            return "alert('密码需包含数字')";
        if (!pwd.matches(".*[a-zA-Z]+.*"))
            return "alert('密码需包含字母')";

        // 根据用户输出的手机号或邮箱号进行找回密码
        switch (i) {
            case 0:
                user.setTel(userNum);
                String str = DigestUtils.md5Hex(pwd);
                user.setPassword(str);
                result = userService.findBackByTel(user);
                break;
            case 1:
                user.setEmail(userNum);
                String str1 = DigestUtils.md5Hex(pwd);
                user.setPassword(str1);
                result = userService.findBackByMail(user);
                break;
        }
        if (!result.isSuccess())
            return "alert('" + result.getMessage() + "')";

        session.invalidate();
        return "location.href='/login'";
    }
}
