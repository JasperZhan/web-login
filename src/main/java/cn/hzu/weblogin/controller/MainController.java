package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;
import cn.hzu.weblogin.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {
        return mainCheck(session, model);
    }

    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        return mainCheck(session, model);
    }

    private String mainCheck(HttpSession session, Model model) {
        String retStr = "";
        if (session.getAttribute("is_login") != null) {
            if (session.getAttribute("is_check") != null) {
                User user = new User();
                user.setUserId((Integer) session.getAttribute("user_id"));
                Result<UserInfo> result = userInfoService.show_userInfo(user);
                if (result.isSuccess()) {
                    UserInfo userInfo = result.getData();
                    model.addAttribute("userInfo", userInfo);
                    retStr = "redirect:main";
                } else {
                    retStr = "redirect:error";
                }
            } else {
                retStr = "redirect:verify";
            }
        } else {
            retStr = "redirect:login";
        }
        return retStr;
    }
}



