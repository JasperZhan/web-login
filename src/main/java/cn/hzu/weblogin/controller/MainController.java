package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;
import cn.hzu.weblogin.service.UserInfoService;
import cn.hzu.weblogin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    UserService userService;

    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {
        return mainCheck(session, model);
    }

    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        return mainCheck(session, model);
    }

    private String mainCheck(HttpSession session, Model model) {

        // 为了防止用户在未登录的情况下进入主页面，必须先判断用户是否已经登录
        // 如果用户已经登录，在登录控制层里面，登录完成会完成 session 的 is_login 写入
        // 未登录用户返回 登录页面
        if (session.getAttribute("is_login") == null) {
            System.out.println("用户未登录");
            return "redirect:login";
        }

        // 用户已登录
        // 获取当前已经登录的用户id
        User user = new User();
        user.setUserId((Integer) session.getAttribute("user_id"));

        // 因为可能存在用户在 session 未过期的情况下重新进入主页
        // 先通过 session 判断用户是否已经完成认定
        // 可以解决反复访问数据库的问题
        if (session.getAttribute("is_check") == null) {

            // 如果用户的 session 中无认定完成数据 is_check
            // 此时有两种情况

            // 获取当前用户的登录信息
            Result<User> result = userService.getUser(user);
            // 查询到该用户
            if (result.isSuccess()) {
                // 用户未认证
                // 1.用户注册完成之后，在完成认定过程中中途退出，出现仅注册未认定的情况，此时应该返回 认证页面
                if (result.getData().getId_Check() == 0) {
                    System.out.println("用户未认证");
                    return "redirect:verify";
                }
                // 数据库访问出错
            } else {
                System.out.println("查询错误1");
                return "redirect:error";
            }
        }

        // 用户已登录已认证
        // 2.用户 session 过期之后第一次登录，未从数据库查询是否认证
        session.setAttribute("is_check", true);
        // 从数据库查询用户的详细信息
        Result<UserInfo> resultUserInfo = userInfoService.show_userInfo(user);
        if (resultUserInfo.isSuccess()) {
            UserInfo userInfo = resultUserInfo.getData();
            model.addAttribute("userInfo", userInfo);
            //数据库访问出错
        } else {
            System.out.println("查询错误2");
            return "redirect:error";
        }
        return "index";
    }
}



