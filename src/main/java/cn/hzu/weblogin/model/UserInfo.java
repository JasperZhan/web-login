package cn.hzu.weblogin.model;

import lombok.Data;

import java.util.Date;

/**
 * @className: User
 * @description: 用户信息类
 * @author: Jsper Zhan
 * @createDate: 2021/10/25
 */

@Data
public class UserInfo {
  long id;
  String userName;              //用户名
  String firstName;             //名字
  String lastName;              //姓
  String idCard;                //身份证号
  String addProvince;           //地址 省
  String addCity;               //地址 市
  String addDistrict;           //地址 区/县
  char sex;                     //性别
  Date birthday;                //生日
}
