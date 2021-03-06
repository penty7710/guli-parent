package com.pty.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 彭天怡 2022/4/19.
 */
@EnableFeignClients
//nacos注册
@EnableDiscoveryClient
@SpringBootApplication
//扫描组件，因为swgger配置类不在当前模块
@ComponentScan(basePackages = {"com.pty"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class);
    }
}
