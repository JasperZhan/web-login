package cn.hzu.weblogin.config;

import cn.hzu.weblogin.controller.LoginController;
import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDao userDao;
    // Controller方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //获取session中用户信息，并比对
        User sessionUser = (User) session.getAttribute(LoginController.SESSION_NAME);
        //如果用户未登录，则重定向至主页
        if (sessionUser == null) {
            response.sendRedirect("/");
            return false;
        }
        User getUser = null;
        try {
            getUser = userDao.getById(sessionUser.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果session中用户信息无效，则重定向至主页
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
    // Controller方法执行之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    // 整个请求完成后（包括Thymeleaf渲染完毕）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
