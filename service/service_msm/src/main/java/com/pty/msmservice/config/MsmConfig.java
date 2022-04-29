package com.pty.msmservice.config;

import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用AK&SK初始化账号Client
 * @author : pety
 * @date : 2022/4/29 10:58
 */
public class MsmConfig {

    public static com.aliyun.dysmsapi20170525.Client Create(String keyid, String keysecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(keyid)
                .setAccessKeySecret(keysecret);

        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

}
