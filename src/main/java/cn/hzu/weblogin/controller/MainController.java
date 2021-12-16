package cn.hzu.weblogin.controller;

import cn.dev33.satoken.stp.StpUtil;
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
//        if (session.getAttribute("is_login") == null) {
//            System.out.println("用户未登录");
//            return "redirect:login";
//        }
        if (!StpUtil.isLogin()) {
            System.out.println("用户未登录");
            return "redirect:login";
        }

        // 用户已登录
        // 获取当前已经登录的用户id
        User user = new User();
        //user.setUserId((Integer) session.getAttribute("user_id"));
        System.out.println(StpUtil.getLoginId());
        user.setUserId(StpUtil.getLoginIdAsInt());
        // 获取当前用户的登录信息
        Result<User> result = userService.getUser(user);
        user = result.getData();

        // 查询失败，数据库错误
        if (!result.isSuccess())
            return "redirect:error";

        // 可能存在情况，用户在注册完成未进行实名认证就退出了，此时是未认证状态
        // 用户未认证
        if (user.getId_Check() == 0) {
            return "redirect:verify";
        }

        // 从数据库查询用户的详细信息
        Result<UserInfo> resultUserInfo = userInfoService.show_userInfo(user);
        if (resultUserInfo.isSuccess()) {
            UserInfo userInfo = resultUserInfo.getData();
            model.addAttribute("user", user);
            model.addAttribute("userInfo", userInfo);
            //数据库访问出错
        } else {
            System.out.println("查询错误2");
            return "redirect:error";
        }
        return "main";
    }
}



