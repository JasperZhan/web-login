package cn.hzu.weblogin.controller;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.model.vo.UserInfo;
import cn.hzu.weblogin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class VerifyController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/verify")
    public String verify() {
        return "verify";
    }

    @ResponseBody
    @RequestMapping("/verify/check")
    public String loginCheck(String id_card, String real_name, HttpSession session) {
        //返回信息
        String retStr;
        UserInfo userInfo = new UserInfo();

        Result<UserInfo> result = null;

        System.out.println(id_card);

        userInfo.setId_Card(id_card);
        userInfo.setReal_name(real_name);
        userInfo.setId((Integer) session.getAttribute("id"));
        System.out.println(userInfo.getId());
        result = userInfoService.verify(userInfo);
        if (result.isSuccess()) {
            retStr = "alert('" + result.getMessage() + "')";
        } else {
            retStr = "alert('" + result.getMessage() + "')";
        }
        return retStr;
    }
}
