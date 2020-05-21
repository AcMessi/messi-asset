package com.tech.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SolutionSpringcloudConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionSpringcloudConfigClientApplication.class, args);
    }

}
