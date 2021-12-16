package cn.hzu.weblogin.config;

/**
 * @author Jasper Zhan
 * @date 2021年11月29日 8:31
 */

import org.springframework.context.annotation.Configuration;

/**
 * 阿里云认证配置
 */
@Configuration
public class VerifyConfig {
    public static final String appcode = "1ca6f76e55d24cc8850a96c92888b06d";

    public static final String host = "https://zid.market.alicloudapi.com";

    public static final String path = "/idcheck/Post";

    public static final String method = "POST";
}
