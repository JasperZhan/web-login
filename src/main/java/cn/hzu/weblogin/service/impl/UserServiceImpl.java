package cn.hzu.weblogin.service.impl;

import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: UserServiceImpl
 * @description: 用户业务处理实现类
 * @author: Hzu_rang
 * @createDate: 2021/10/25
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> register(User user) {
        Result<User> result = new Result<>();
        //先去数据库找用户名是否存在
        User getUser = null;
        try {
            getUser = userDao.getByPhone(user.getTel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUser != null) {
            result.setResultFailed("该手机号已注册！");
            return result;
        }
        //加密储存用户的密码
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        //存入数据库
        userDao.add(user);
        //返回用户数据，成功消息
        result.setResultSuccess("注册用户成功！", user);
        return result;
    }


    @Override
    public Result<User> login(User user) {
        Result<User> result = new Result<>();
        //去数据库查找用户
        User getUser = null;
        try {
            getUser = userDao.getByPhone(user.getTel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        //比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            result.setResultFailed("手机号或者密码错误！");
            return result;
        }
        //设定登录成功消息以及用户信息
        result.setResultSuccess("登录成功！", getUser);
        return result;
    }
}