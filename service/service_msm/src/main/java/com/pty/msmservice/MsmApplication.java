package com.pty.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : pety
 * @date : 2022/4/29 10:19
 */
@SpringBootApplication
@ComponentScan("com.pty")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class);
    }
}
