package cn.hzu.weblogin.service;

import cn.hzu.weblogin.model.Code;
import cn.hzu.weblogin.model.Result;

import javax.servlet.http.HttpSession;

/**
 * @interfaceName: SmsService
 * @description: 短信服务
 * @author: Hzu_rang
 * @createDate: 2021/10/28
 */
public interface SmsService {
    /**
     * 发送短信
     * @param tel 手机号码
     * @return Result<String>
     */
    Result<Code> sendByPhone(String tel);

    /**
     *
     * @param email 邮箱号码
     * @return Result<String>
     */
    Result<Code> sendByEmail(String email);
}
