package cn.hzu.weblogin.service.impl;

import cn.hzu.weblogin.config.VerifyConfig;
import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.dao.UserInfoDao;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;
import cn.hzu.weblogin.service.UserInfoService;
import cn.hzu.weblogin.utils.HttpUtils;
import cn.hzu.weblogin.utils.IDValidateUtils;
import cn.hzu.weblogin.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: UserInfoServiceImpl
 * @description: 用户信息处理实现类
 * @author: Hzu_rang
 * @createDate: 2021/10/28
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Result<UserInfo> verify(UserInfo userInfo) {
        User user = new User();
        user.setUserId(userInfo.getId());

        Result<UserInfo> resultUserInfo = new Result<>();

        if (!IDValidateUtils.check(userInfo.getId_Card())) {
            resultUserInfo.setResultFailed("身份证号码不合法");
            return resultUserInfo;
        }

        Map<String, String> headers = new HashMap<String, String>();

        headers.put("Authorization", "APPCODE " + VerifyConfig.appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cardNo", userInfo.getId_Card());
        bodys.put("realName", userInfo.getReal_name());

        try {
            HttpResponse response = HttpUtils.doPost(VerifyConfig.host, VerifyConfig.path, VerifyConfig.method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                System.out.println("正常请求计费(其他均不计费)");
                System.out.println("获取返回的json：");
                String str = EntityUtils.toString(response.getEntity());

                JSONObject jsonObject1 = JSON.parseObject(str);
                String error_code = jsonObject1.getString("error_code");
                String result = jsonObject1.getString("result");

                JSONObject jsonObject2 = JSON.parseObject(result);
                String isok = jsonObject2.getString("isok");

                String IdCardInfor = jsonObject2.getString("IdCardInfor");

                JSONObject jsonObject3 = JSON.parseObject(IdCardInfor);
                String province = jsonObject3.getString("province");
                String city = jsonObject3.getString("city");
                String district = jsonObject3.getString("district");
                String sex = jsonObject3.getString("sex");
                String birthday = jsonObject3.getString("birthday");

                if (error_code.equals("0") && isok.equals("true")) {
                    // 保留一段更新已经实名认证成功的信息
                    userInfo.setAddProvince(province);
                    userInfo.setAddCity(city);
                    userInfo.setAddDistrict(district);
                    if (sex.equals("男")) {
                        userInfo.setSex(1);
                    } else if (sex.equals("女")) {
                        userInfo.setSex(0);
                    }
                    java.sql.Date date = java.sql.Date.valueOf(birthday);
                    userInfo.setBirthday(date);
                    resultUserInfo.setResultSuccess("实名认证成功，即将跳转！", userInfo);
                    userInfoDao.adduserInfo(userInfo);
                    userDao.changeId_check(user.getUserId(), 1);
                } else if (error_code.equals("0") && isok.equals("flase")) {
                    resultUserInfo.setResultFailed("实名认证信息不匹配，请重新输入！");
                }
            } else {
                System.out.println("参数名错误 或 其他错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        //加密储存用户的密码
//        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
//        //存入数据库
//        userDao.add(user);
        return resultUserInfo;
    }

    @Override
    public Result<UserInfo> show_userInfo(User user) {
        Result<UserInfo> resultUserInfo = new Result<>();
        UserInfo userInfo = new UserInfo();
        userInfo = userInfoDao.getUserInfoById(user.getUserId());
        if (userInfo == null) {
            resultUserInfo.setResultFailed("用户信息获取失败");
        } else {
            resultUserInfo.setResultSuccess("用户信息查找成功", userInfo);
        }
        return resultUserInfo;
    }
}
