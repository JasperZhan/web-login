package cn.hzu.weblogin.service.impl;

import cn.hzu.weblogin.model.Code;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.service.SmsService;
import cn.hzu.weblogin.utils.RandomUtils;
import cn.hzu.weblogin.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: SmsServiceImpl
 * @description: 短信业务逻辑
 * @author: Hzu_rang
 * @createDate: 2021/10/31
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    private static String APP_ID;
    private static String APP_KEY;
    private static String TEMPLATE_ID;

    @Value("${spring.project.sms.apiUrl}")
    private String apiUrl;

    @Value("${spring.project.sms.timeOut}")
    private Integer timeOut;

    @Value("${spring.project.sms.appId}")
    public void setAppId(String appId) {
        APP_ID = appId;
    }

    @Value("${spring.project.sms.appSecret}")
    public void setAppKey(String appKey) {
        APP_KEY = appKey;
    }

    @Value("${spring.project.sms.template-id}")
    public void setTemplateId(String templateId) {
        TEMPLATE_ID = templateId;
    }

    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送短信验证码
     * @param tel 手机号码
     * @return
     */
    @Override
    public Result<Code> sendByPhone(String tel) {
        // 校验手机号码合法
        Result<Code> result = new Result<>();
        Code code = new Code();

        if (!StringUtils.isValidTel(tel)) {
            result.setResultFailed("手机号码不合法");
            return result;
        }
        String num = RandomUtils.number(6);

        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, APP_ID, APP_KEY);
        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject json = null;
        map.put("number", tel);
        map.put("templateId", TEMPLATE_ID);

        // 添加模板参数
        String[] templateParams = new String[2];
        templateParams[0] = num;
        templateParams[1] = "1分钟";
        map.put("templateParams", templateParams);
        try {
            String result_sms = client.send(map);
            json = JSONObject.parseObject(result_sms);
            System.out.println(json);
            if (json.getIntValue("code") != 0) {
                // TODO 处理服务端错误码
                log.error("验证码发送失败，手机号：{}，错误信息：{}", tel, "发送短信失败");
                result.setResultFailed("发送短信失败");
                return result;
            } else {
                code.setCode(num);
                code.setCurrentTime(System.currentTimeMillis() / 1000);
                result.setResultSuccess("发送短信成功", code);
                return result;
            }
        } catch (Exception e) {
            String str;
            if (e == null) {
                str = "";
            }
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            str = stringWriter.toString();
            log.error("验证码发送出现异常：{}", str);
            result.setResultFailed("发送短信失败");
            return result;
        }
    }

    /**
     * 发送邮箱验证码
     * @param email 邮箱号码
     * @return
     */
    @Override
    public Result<Code> sendByEmail(String email) {
        Result<Code> result = new Result<>();
        Code code = new Code();
        if (!StringUtils.isEmail(email)) {
            result.setResultFailed("邮箱号码不合法");
            return result;
        }
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String num = RandomUtils.number(6);

            mailMessage.setText("【党建服务系统】你的验证码为："+ num + "，有效时间为1分钟(若不是本人操作，可忽略该条邮件)");//内容
            mailMessage.setTo(email);//发给谁
            mailMessage.setFrom(from);//你自己的邮箱
            mailMessage.setSubject("党建服务系统");
            mailSender.send(mailMessage);//发送
            code.setCode(num);
            code.setCurrentTime(System.currentTimeMillis() / 1000);
            log.info("文本邮件发送成功！");
            result.setResultSuccess("邮件发送成功", code);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setResultFailed("邮件发送失败");
            return result;
        }
    }

}
