package cn.hzu.weblogin.dao;

import cn.hzu.weblogin.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserDao
 *
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
    int addByPhone(User user);

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增成功记录条数
     */
    int addByMail(User user);

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
     * 根据手机号码获取用户
     *
     * @param user_phone 手机号码
     * @return 用户对象
     */
    User getByPhone(@Param("user_phone") String user_phone);

    /**
     * 根据邮箱获取用户
     *
     * @param user_email 邮箱
     * @return 用户对象
     */
    User getByEmail(@Param("user_email") String user_email);

    /**
     * 改变实名认证状态
     * @param id 对应的字段名
     * @param check_status 认证状态
     * @return 改变成功记录条数
     */
    int changeId_check(Integer id, Integer check_status);

    /**
     * 根据手机号更新密码
     * @param user_phone 手机号
     * @param user_pwd 密码
     * @return 更新成功记录条数
     */
    int updateByPhone(@Param("user_phone") String user_phone, @Param("user_pwd") String user_pwd);

    /**
     * 根据邮箱更新密码
     * @param user_email 邮箱
     * @param user_pwd 密码
     * @return 更新成功记录条数
     */
    int updateByEmail(@Param("user_email") String user_email, @Param("user_pwd") String user_pwd);

    /**
     * 根据手机号更新信息
     * @param id 用户id
     * @param user_email 邮箱号码
     * @return 更新成功记录条数
     */
    int updatePhone(Integer id, @Param("user_email") String user_email);

    /**
     * 根据邮箱更新信息
     * @param id 用户id
     * @param user_phone
     * @return 更新成功记录条数
     */
    int updateEmail(Integer id, @Param("user_phone") String user_phone);

    /**
     * 根据手机号码删除信息
     * @param id 用户id
     * @param user_phone 手机号码
     * @return 删除成功记录条数
     */
    int deleteByPhone(Integer id, @Param("user_phone") String user_phone);

    /**
     * 根据邮箱号码删除信息
     * @param id 用户id
     * @param user_email 邮箱号码
     * @return 删除成功记录条数
     */
    int deleteByEmail(Integer id, @Param("user_email") String user_email);
}
