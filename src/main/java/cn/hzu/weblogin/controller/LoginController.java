package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserController {
    public static final String SESSION_NAME = "userInfo";
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    /**
     * 用户注册
     *
     * @param user    传入注册用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result;
        //如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        //调用注册服务
        result = userService.register(user);
        //如果注册成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }
    /**
     * 用户登录
     *
     * @param user    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result;
        //如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        //调用登录服务
        result = userService.login(user);
        //如果登录成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }
    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/islogin")
    public Result<User> isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Result<User> result = new Result<>();
        //从session里面获取用户信息
        User sessionUser = (User) session.getAttribute(SESSION_NAME);
        //如果从session中获取用户信息为空，则说明没有登录
        if (sessionUser == null) {
            result.setResultFailed("用户未登录！");
            return result;
        }
        //若用户登录，利用里面的信息去数据库查找并进行比对，保证信息正确性
        User getUser = null;
        try {
            getUser = userDao.getByPhone(sessionUser.getTel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
            result.setResultFailed("用户信息无效！");
            return result;
        }
        result.setResultSuccess("用户已登录！", getUser);
        return result;
    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        //用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setResultSuccess("用户退出登录成功！", null);
        return result;
    }
}
