package cn.hzu.weblogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemonstrationController {
    @RequestMapping("/demo")
    public String demo() {
        return "dsc";
    }
}
