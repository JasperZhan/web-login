package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @className: LoginController
 * @description: 用户登录接口
 * @author: Hzu_rang
 * @createDate: 2021/10/25
 */
@Controller
public class LoginController {
    public static final String SESSION_NAME = "userInfo";
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 用户登录
     *
     * @param loginWay 用户登录方式（0：手机登录；1：邮箱登录；2：人脸识别登录）
     * @param userNum  用户登录账户（手机号码或者邮箱号码）
     * @param userPw   用户登录密码
     * @return 登录结果
     */
    @ResponseBody
    @RequestMapping("/login/check")
    public String loginCheck(String loginWay, String userNum, String userPw, HttpSession session) {

        //返回信息
        String retStr = "";
        User user = new User();
        Result<User> result = null;

        System.out.println(loginWay);
        int i = Integer.valueOf(loginWay).intValue();

        switch (i) {
            case 0:
                user.setTel(userNum);
                user.setPassword(userPw);
                result = userService.loginByTel(user);
                break;
            case 1:
                user.setEmail(userNum);
                user.setPassword(userPw);
                result = userService.loginByMail(user);
                break;
        }

        if (result.isSuccess()) {
            session.setAttribute("user_id", result.getData().getUserId());
            session.setAttribute("is_login", true);
            retStr = "location.href='/main'";
        } else {
            retStr = "alert('" + result.getMessage() + "')";
        }

        return retStr;
    }

    /**
     * 用户登出
     *
     * @param session 对象，用于操作session
     * @return 结果对象
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}
