package cn.hzu.weblogin.service;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import org.springframework.stereotype.Service;

/**
 * @interfaceName: UserService
 * @description: 用户业务处理接口
 * @author: Hzu_rang
 * @createDate: 2021/10/23
 */
@Service
public interface UserService {
    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return 注册结果
     */
    Result<User> register(User user);
    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return 登录结果
     */
    Result<User> login(User user);
}
