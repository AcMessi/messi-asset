package com.tech.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
@MapperScan("com.tech.asset")
@ComponentScan({"com.tech.mq.modules.swagger", "com.tech.mq.modules.activemq"})
public class SolutionMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionMqApplication.class, args);
    }

}
