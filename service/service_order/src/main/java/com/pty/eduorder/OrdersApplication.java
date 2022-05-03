package com.pty.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : pety
 * @date : 2022/5/3 9:13
 */
//远程调用
@EnableFeignClients
//nacos注册
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.pty"})
@MapperScan("com.pty.eduorder.mapper")
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class);
    }
}
