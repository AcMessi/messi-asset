package com.tech.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: 安全配置类
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/7
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 刷新token需要
     */
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 让Security 忽略这些url，不做拦截处理
     *
     * @param
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers
                ("/swagger-ui.html/**", "/webjars/**",
                        "/swagger-resources/**", "/v2/api-docs/**",
                        "/swagger-resources/configuration/ui/**", "/swagger-resources/configuration/security/**",
                        "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 配置当前http security要生效的url
                .requestMatchers()
                .antMatchers("/auth/login", "/login/form", "/oauth/authorize")
                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers("/auth/login", "/login/form")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin()
                // 登录页面
                .loginPage("/auth/login")
                // 登录处理url
                .loginProcessingUrl("/login/form");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * password 模式需要此bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

