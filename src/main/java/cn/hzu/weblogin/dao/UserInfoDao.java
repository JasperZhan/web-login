package cn.hzu.weblogin.dao;

import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @interfaceName: UserInfoDao
 * @description: 用户信息数据库操作接口
 * @author: Hzu_rang
 * @createDate: 2021/10/28
 */
@Mapper
public interface UserInfoDao {
    /**
     * 修改用户信息
     *
     * @param userInfo 用户对象
     * @return 修改成功记录条数
     */
    int adduserInfo(UserInfo userInfo);
}
