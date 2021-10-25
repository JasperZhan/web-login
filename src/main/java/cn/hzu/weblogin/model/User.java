package cn.hzu.weblogin.model;

import lombok.Data;

/**
 * @className: User
 * @description: 用户实体，主要用于登录
 * @author: Jsper Zhan
 * @createDate: 2021/10/23
 */
@Data
public class User {
  int id;
  String tel;                   //用户手机号码
  String email;                 //用户邮箱号
  String password;              //用户密码
  Boolean isCheck;              //判断用户是否已实名认证
}
