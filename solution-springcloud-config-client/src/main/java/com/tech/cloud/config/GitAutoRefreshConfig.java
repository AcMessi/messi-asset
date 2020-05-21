package com.tech.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 正确示例，
 * 我们发送 POST 请求到 http://localhost:8116/actuator/refresh 这个接口，用 postman 之类的工具即可，此接口就是用来触发加载新配置的
 * 接口获取到的配置始终是旧的，说明 refresh 机制不起作用了，这与 @Value 注解的实现有关，所以，我们在项目中就不要使用这种方式加载配置了。
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/21
 */
@Component
@Data
@ConfigurationProperties(prefix = "data")
public class GitAutoRefreshConfig {

    private String test1;

    private String test2;
}