package com.tech.redisson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SolutionRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionRedissonApplication.class, args);
    }

}
