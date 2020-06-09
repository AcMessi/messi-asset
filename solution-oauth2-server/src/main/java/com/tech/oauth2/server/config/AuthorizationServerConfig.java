package com.tech.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Description: 认证服务器配置类
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/7
 */
@Configuration
@EnableAuthorizationServer // 开启了认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置被允许访问此认证服务器的客户端信息
     * 1.内存方式
     * 2. 数据库方式
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 基于内存模式管理客户端
        clients.inMemory()
                .withClient("test") // 客户端id
                .secret(passwordEncoder.encode("test123")) // 加密，客户端密码
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                .scopes("all") // 授权范围标识，哪部分资源可访问（all只是标识，不是说所有资源）
                .autoApprove(false) // false 跳到一个授权页面手动点击授权，true不需要手动点授权，直接响应一个授权码
                .redirectUris("http://locahost:9100")// 客户端回调地址
                .accessTokenValiditySeconds(60 * 60 * 8) //访问令牌有效时长 默认为12小时
                .refreshTokenValiditySeconds(60 * 60 * 24 * 60); // 刷新令牌有效时长,默认是30天
//        http://localhost:8201/oauth/authorize?client_id=test&response_type=code
//        http:localhost:8201/oauth/token
//        Authorization: Basic client_id/client_secret
//        code = KfeCcT & grant_type = authorization_code
    }
}
