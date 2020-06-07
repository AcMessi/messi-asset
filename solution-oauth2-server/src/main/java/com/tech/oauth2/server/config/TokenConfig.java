package com.tech.oauth2.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @Description: token配置类
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/7
 */
@Configuration
public class TokenConfig {

    public static final String SIGNING_KEY = "test-key";

    // jdbc管理token
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称加密进行签名令牌，资源服务器也要采用此密钥来进行解密,来校验令牌合法性
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        // Jwt管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
