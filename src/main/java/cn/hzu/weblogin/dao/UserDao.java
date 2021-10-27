package cn.hzu.weblogin.dao;

import cn.hzu.weblogin.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @interfaceName: UserDao
 * @description: 用户数据库操作接口
 * @author: Hzu_rang
 * @createDate: 2021/10/25
 */
@Mapper
public interface UserDao {
    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增成功记录条数
     */
    int add(User user);
    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 修改成功记录条数
     */
    int update(User user);
    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户对象
     */
    User getById(Integer id);
    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    User getByUsername(String username);
    /**
     * 根据手机号码获取用户
     *
     * @param user_phone 手机号码
     * @return 用户对象
     */
    User getByPhone(String user_phone);
    /**
     * 根据邮箱获取用户
     *
     * @param user_email 邮箱
     * @return 用户对象
     */
    User getByEmail(String user_email);
}
