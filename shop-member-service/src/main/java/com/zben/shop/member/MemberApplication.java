package com.zben.shop.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 12:21
 */
@SpringBootApplication(scanBasePackages = "com.zben.shop")
@EnableEurekaClient
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
