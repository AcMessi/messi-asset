package com.tech.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SolutionOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionOauth2ServerApplication.class, args);
    }

}
