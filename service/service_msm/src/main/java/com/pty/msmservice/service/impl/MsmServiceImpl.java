package com.pty.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pty.msmservice.config.MsmConfig;
import com.pty.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author : pety
 * @date : 2022/4/29 10:16
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Value("${aliyun.msm.file.keyid}")
    private String keyid;

    @Value("${aliyun.msm.file.keysecret}")
    private String keysecret;

    //发送短信的方法
    @Override
    public boolean send(String code, String phone)  {
        if(StringUtils.isEmpty(phone)){
            return false;
        }

        Client client = null;
        try {
            client = MsmConfig.Create(keyid, keysecret);
        } catch (Exception e) {
            return false;
        }


        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phone)
                //这里拼接字符串一定要正确，否则发不出去
                .setTemplateParam("{\"code\":\""+code+"\"}");
        try {
            System.out.println("{\"code\":\""+code+"\"}");
            SendSmsResponse response = client.sendSms(sendSmsRequest);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
