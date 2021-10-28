package cn.hzu.weblogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @ResponseBody
    @RequestMapping("/main")
    public String hello() {
        return "登录成功";
    }
}
