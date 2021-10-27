package cn.hzu.weblogin.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: User
 * @description: 用户实体，主要用于登录注册
 * @author: Hzu_rang
 * @createDate: 2021/10/23
 */
@Data
public class User {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer userId;
    /**
     * 用户手机号码
     */
    private String tel;
    /**
     * 用户邮箱号
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 判断用户是否已经实名认证（0：未实名认证）
     */
    private int id_Check;
    /**
     * 人脸唯一标识
     */
    private String faceToken;
    /**
     * 第三方登录类型，未接入
     */
    private int thirdLogin;
    /**
     * 第三方登录ID
     */
    private String thirdLoginId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
