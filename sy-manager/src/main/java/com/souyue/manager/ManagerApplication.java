package com.souyue.manager;

import com.souyue.common.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author shkstart
 * @create 2019-10-03 21:07
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
