package cn.hzu.weblogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {
  @RequestMapping("/login")
  public String hello() {
    return "login";
  }
}
