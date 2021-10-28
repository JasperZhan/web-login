package cn.hzu.weblogin.service;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;

/**
 * @interfaceName: UserService
 * @description: 用户验证处理接口
 * @author: Hzu_rang
 * @createDate: 2021/10/23
 */

public interface UserService {
    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return 注册结果
     */
    Result<User> register(User user);

    /**
     * 用户手机登录
     *
     * @param user 用户对象
     * @return boolean 是否成功
     */
    Result<User> loginByTel(User user);

    /**
     * 用户邮箱登录
     *
     * @param user 用户对象
     * @return boolean 是否成功
     */
    Result<User> loginByMail(User user);
}
