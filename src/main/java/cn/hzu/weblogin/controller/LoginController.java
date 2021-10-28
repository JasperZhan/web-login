package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

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
            retStr = "location.href='/main'";
        } else {
            retStr = "alert('" + result.getMessage() + "')";
        }

        return retStr;

//        如果登录成功，则设定session
//        if (result.isSuccess()) {
//            request.getSession().setAttribute(SESSION_NAME, result.getData());
//        }
//        return result;
    }


    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
//    @GetMapping("/islogin")
//    public Result<User> isLogin(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Result<User> result = new Result<>();
//        //从session里面获取用户信息
//        User sessionUser = (User) session.getAttribute(SESSION_NAME);
//        //如果从session中获取用户信息为空，则说明没有登录
//        if (sessionUser == null) {
//            result.setResultFailed("用户未登录！");
//            return result;
//        }
//        //若用户登录，利用里面的信息去数据库查找并进行比对，保证信息正确性
//        User getUser = null;
//        try {
//            getUser = userDao.getByPhone(sessionUser.getTel());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
//            result.setResultFailed("用户信息无效！");
//            return result;
//        }
//        result.setResultSuccess("用户已登录！", getUser);
//        return result;
//    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
//    @GetMapping("/logout")
//    public Result logout(HttpServletRequest request) {
//        Result result = new Result();
//        //用户登出很简单，就是把session里面的用户信息设为null即可
//        request.getSession().setAttribute(SESSION_NAME, null);
//        result.setResultSuccess("用户退出登录成功！", null);
//        return result;
//    }
}
