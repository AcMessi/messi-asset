package com.tech.cloud.controller;

import com.tech.cloud.config.GitAutoRefreshConfig;
import com.tech.cloud.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/20
 */
@RestController
@RequestMapping("api/test/")
@RefreshScope // 配置文件自动刷新
public class TestController {

    @Autowired
    private GitConfig gitConfig;

    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @GetMapping(value = "getConfigByData")
    public Object show() {
        return gitConfig;
    }

    @GetMapping(value = "getConfigByConfigurationProperties")
    public Object autoShow() {
        return gitAutoRefreshConfig;
    }
}
