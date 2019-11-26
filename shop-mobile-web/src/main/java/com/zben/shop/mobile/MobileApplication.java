package com.zben.shop.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/26 0026 10:29
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }
}
