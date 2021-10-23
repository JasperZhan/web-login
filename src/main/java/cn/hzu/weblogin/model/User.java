package cn.hzu.weblogin.model;

import lombok.Data;

/**
 * model: User
 * 描述：用户实体
 */
@Data
public class User {
  int id;
  String account_id;            //用户账户名
  String phone;                 //用户手机号码
  String email;                 //用户邮箱号
  String password;              //用户密码
}
