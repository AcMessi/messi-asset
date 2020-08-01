package com.tech.cloud.web.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/18
 */
@Controller
public class SwaggerViewConfig {

    @GetMapping("/")
    public String forwardSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
