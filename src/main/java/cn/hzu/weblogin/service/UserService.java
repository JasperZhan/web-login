package cn.hzu.weblogin.service;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;

/**
 * @interfaceName: UserService
 * @description: 用户验证处理接口
 * @author: Hzu_rang
 * @createDate: 2021/10/28
 */
public interface UserService {
    /**
     * 用户手机注册
     *
     * @param user 用户对象
     * @return 注册结果
     */
    Result<User> registerByTel(User user);

    /**
     * 用户邮箱注册
     *
     * @param user 用户对象
     * @return 注册结果
     */
    Result<User> registerByMail(User user);

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

    /**
     * 查找用户信息
     *
     * @param user 用户对象
     * @return 查找结果
     */
    Result<User> getUser(User user);

    /**
     * 根据手机号码找回密码
     * @param user 用户对象
     * @return 重置密码结果
     */
    Result<User> findBackByTel(User user);

    /**
     * 根据邮箱号找回密码
     * @param user 用户对象
     * @return 重置密码结果
     */
    Result<User> findBackByMail(User user);

    /**
     * 根据手机号码完善信息
     * @param user 用户对象
     * @return 修改信息结果
     */
    Result<User> addInfoByTel(User user);
    /**
     * 根据邮箱号完善信息
     * @param user 用户对象
     * @return 修改信息结果
     */
    Result<User> addInfoByMail(User user);
}
