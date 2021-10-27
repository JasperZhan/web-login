package cn.hzu.weblogin.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @className: User
 * @description: 用户信息类
 * @author: Hzu_rang
 * @createDate: 2021/10/25
 */

@Data
public class UserInfo {
    /**
     * id
     */
    private long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户真实姓名
     */
    private String real_name;
    /**
     * 用户身份证号
     */
    private String id_Card;
    /**
     * 学生证号
     */
    private String stu_id_Card;
    /**
     * 省份
     */
    private String addProvince;
    /**
     * 市区
     */
    private String addCity;
    /**
     * 区/县
     */
    private String addDistrict;
    /**
     * 性别
     */
    private int sex;
    /**
     * 生日
     */
    private Date birthday;
}
