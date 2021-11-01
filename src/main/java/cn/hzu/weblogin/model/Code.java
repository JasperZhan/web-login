package cn.hzu.weblogin.model;

import lombok.Data;

import java.util.Date;

/**
 * @className: Code
 * @description: 验证码实体类
 * @author: Hzu_rang
 * @createDate: 2021/10/31
 */
@Data
public class Code {
    /**
     * 验证码
     */
    private String code;

    /**
     * 当前时间
     */
    private Long currentTime;
}
