package cn.hzu.weblogin.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;
import cn.hzu.weblogin.service.UserInfoService;
import cn.hzu.weblogin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class VerifyController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserService userService;

    @RequestMapping("/verify")
    public String verify(HttpSession session) {
        if (verifyStatus(session)) {
            return "verify";
        } else {
            return "verifyError";
        }
    }

    @ResponseBody
    @RequestMapping("/verify/check")
    public String check(String id_card, String real_name, HttpSession session) {

//        if (userInfoService.checkId_exist)
        if (!verifyStatus(session)) {
            return "verifyError";
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setId_Card(id_card);
        userInfo.setReal_name(real_name);
        userInfo.setId((Integer) session.getAttribute("user_id"));

        Result<UserInfo> result = userInfoService.verify(userInfo);

        if (result.isSuccess()) {
            return "location.href='/main'";
        } else {
            return "verifyError";
        }
    }

    /**
     * 判断用户状态
     * 只有已登录未注册的用户才允许进入实名认证页面
     *
     * @return boolean true：运行进入 false：非法进入
     */
    private boolean verifyStatus(HttpSession session) {
        //用户未登录
        if (!StpUtil.isLogin()) {
            return false;
        }
//        if (session.getAttribute("is_login") == null) {
//
//        }
        User user = new User();
//        user.setUserId((Integer) session.getAttribute("user_id"));
        user.setUserId(StpUtil.getLoginIdAsInt());
        Result<User> result = userService.getUser(user);
        if (result.isSuccess()) {
            // 用户已认证
            if (result.getData().getId_Check() == 1) {
                return false;
            }
        } else {
            // 由于 session 的 is_login 状态，是在登录后写入的，所以这里不需要判断用户是否存在
            // 数据库访问出错，暂停进入
            return false;
        }
        return true;
    }
}
