package com.pty.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : pety
 * @date : 2022/4/29 13:44
 */
@EnableDiscoveryClient
@ComponentScan("com.pty")
@SpringBootApplication
@MapperScan("com.pty.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class );
    }
}
