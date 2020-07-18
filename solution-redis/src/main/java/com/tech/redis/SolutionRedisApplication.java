package com.tech.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tech.redis.dao")
public class SolutionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionRedisApplication.class, args);
    }

}
