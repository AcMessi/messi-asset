package com.tech.oauth2.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @Description: 资源服务器相关配置类
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/10
 */
@Configuration
@EnableResourceServer // 标识为资源服务器，请求服务中的资源，就要带着token过来，找不到token或token是无效访问不了资源
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别权限控制
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//    public static final String RESOURCE_ID = "test_server";
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        // 当前资源服务器的资源id，认证服务会认证客户端有没有访问这个资源id的权限，有则可以访问当前服务
//        resources.resourceId(RESOURCE_ID)
//                .tokenServices(tokenService())
//        ;
//    }

    public ResourceServerTokenServices tokenService() {
        // 远程认证服务器进行校验 token 是否有效
        RemoteTokenServices service = new RemoteTokenServices();
//        // 请求认证服务器校验的地址，默认情况 这个地址在认证服务器它是拒绝访问，要设置为认证通过可访问
//        service.setCheckTokenEndpointUrl("http://localhost:8201/oauth/check_token");
        service.setClientId("sc7ckvb3WO");
        service.setClientSecret("c23100c9-dc59-412b-bbd9-d438db44db83");
        return service;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .access("#oauth2.hasScope('all')");
    }
}
