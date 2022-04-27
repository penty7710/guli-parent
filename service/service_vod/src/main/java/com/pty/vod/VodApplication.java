package com.pty.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
/**
 * Created by 彭天怡 2022/4/26.
 */
@EnableDiscoveryClient
@ComponentScan(basePackages ={"com.pty"} )
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class);
    }
}
