package cn.hzu.weblogin.service;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;

/**
 * @interfaceName: UserInfoService
 * @description: 用户个人信息处理接口
 * @author: Hzu_rang
 * @createDate: 2021/10/28
 */
public interface UserInfoService {
    /**
     * 用户实名认证
     *
     * @param userInfo 用户信息对象
     * @return 实名认证结果
     */
    Result<UserInfo> verify(UserInfo userInfo);
}
