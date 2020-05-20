package com.tech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.tech.dao")
public class SolutionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionRedisApplication.class, args);
    }

}
