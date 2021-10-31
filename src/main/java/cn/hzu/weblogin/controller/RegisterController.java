package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
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

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @RequestMapping("/register/check")
    public String registerCheck(String registerWay, String userNum, String userPw, HttpSession session) {
        String retStr = "";
        User user = new User();
        Result<User> result = null;

        System.out.println(registerWay);
        int i = Integer.valueOf(registerWay).intValue();

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

        if (result.isSuccess()) {
            retStr = "location.href='/login'";
        } else {
            retStr = "alert('" + result.getMessage() + "')";
        }

        return retStr;
    }

}
