package cn.hzu.weblogin.service.impl;

import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @description: 用户业务处理实现类
 * @author: Hzu_rang
 * @createDate: 2021/10/25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> registerByTel(User user) {
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
//        DigestUtils.md5Hex(user.getPassword())
//        user.setPassword(user.getPassword());
        //存入数据库
        userDao.addByPhone(user);
        //返回用户数据，成功消息
        result.setResultSuccess("注册用户成功！", user);
        return result;
    }

    @Override
    public Result<User> registerByMail(User user) {
        Result<User> result = new Result<>();
        //先去数据库找用户名是否存在
        User getUser = null;
        try {
            getUser = userDao.getByEmail(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUser != null) {
            result.setResultFailed("该邮箱号已注册！");
            return result;
        }
        //加密储存用户的密码
//        DigestUtils.md5Hex(user.getPassword())
//        user.setPassword(user.getPassword());
        //存入数据库
        userDao.addByMail(user);
        //返回用户数据，成功消息
        result.setResultSuccess("注册用户成功！", user);
        return result;

    }

    @Override
    public Result<User> loginByTel(User user) {
        Result<User> result = new Result<>();

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
        if (!getUser.getPassword().equals(user.getPassword())) {
            System.out.println(getUser.getPassword());
            System.out.println(user.getPassword());
            result.setResultFailed("手机号或者密码错误！");
            return result;
        }
        //设定登录成功消息以及用户信息
        result.setResultSuccess("登录成功！", getUser);
        return result;
    }

    @Override
    public Result<User> loginByMail(User user) {
        Result<User> result = new Result<>();

        User getUser = null;
        try {
            getUser = userDao.getByEmail(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        //比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(user.getPassword())) {
            System.out.println(getUser.getPassword());
            System.out.println(user.getPassword());
            result.setResultFailed("邮箱号或者密码错误！");
            return result;
        }
        //设定登录成功消息以及用户信息
        result.setResultSuccess("登录成功！", getUser);
        return result;
    }
}
