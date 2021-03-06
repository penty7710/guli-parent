package com.pty.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : pety
 * @date : 2022/4/29 13:44
 */
//开启定时任务
@EnableScheduling
@EnableDiscoveryClient
@ComponentScan("com.pty")
@SpringBootApplication
@MapperScan("com.pty.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class );
    }
}
