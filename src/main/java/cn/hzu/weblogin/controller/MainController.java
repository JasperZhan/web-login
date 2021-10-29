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

        String retStr = "";

        // 为了防止用户在未登录的情况下进入主页面，必须先判断用户是否已经登录
        // 如果用户已经登录，在登录控制层里面，登录完成会完成 session 的 is_login 写入
        if (session.getAttribute("is_login") != null) {

            // 获取当前已经登录的用户id
            User user = new User();
            user.setUserId((Integer) session.getAttribute("user_id"));

            // 因为可能存在用户在 session 未过期的情况下重新进入主页
            // 先通过 session 判断用户是否已经完成认定
            // 可以解决反复访问数据库的问题
            if (session.getAttribute("is_check") != null) {

                // 返回主页面，内容为用户的全部信息
                Result<UserInfo> result = userInfoService.show_userInfo(user);

                // 查询结果成功
                if (result.isSuccess()) {
                    UserInfo userInfo = result.getData();
                    model.addAttribute("userInfo", userInfo);
                    retStr = "redirect:main";
                } else {
                    retStr = "alert('数据库访问出错')";
                }

                // 如果用户的 session 中无认定完成数据 is_check
                // 此时有两种情况，
            } else {
                Result<User> result = userService.getUser(user);
                if (result.isSuccess()) {

                    // 1.用户 session 过期之后第一次登录，未从数据库查询是否认证
                    if (result.getData().getId_Check() == 1) {
                        session.setAttribute("is_check", true);
                        retStr = "redirect:main";

                        // 2.用户注册完成之后，在完成认定过程中中途退出，出现仅注册未认定的情况，此时应该返回 认证页面
                    } else {
                        retStr = retStr = "redirect:verify";
                    }
                } else {
                    retStr = "alert('数据库访问出错')";
                }
            }

            // 用户未登录，不允许进入主页面，返回登录页面
        } else {
            retStr = "redirect:login";
        }
        return retStr;
    }
}



