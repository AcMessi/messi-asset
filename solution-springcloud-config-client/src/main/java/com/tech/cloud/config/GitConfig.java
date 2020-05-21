package com.tech.cloud.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 错误示例，接口获取到的配置始终是旧的，说明 refresh 机制不起作用了，这与 @Value 注解的实现有关，所以，我们在项目中就不要使用这种方式加载配置了。
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/21
 */
@Data
@Component
public class GitConfig {

    @Value("${data.test1}")
    private String username;

    @Value("${data.test2}")
    private String password;

}
