package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class RegisterController {
    @Resource
    UserService userService;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 用户注册
     *
     * @param user    传入注册用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 注册结果
     */
//    @PostMapping("/register")
//    public Result<User> register(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
//        Result<User> result;
//        //如果校验有错，返回注册失败以及错误信息
//        if (errors.hasErrors()) {
//            result = new Result<>();
//            result.setResultFailed(errors.getFieldError().getDefaultMessage());
//            return result;
//        }
//        //调用注册服务
//        result = userService.register(user);
//        //如果注册成功，则设定session
////    if (result.isSuccess()) {
////      request.getSession().setAttribute(SESSION_NAME, result.getData());
////    }
//        return result;
//    }
}
