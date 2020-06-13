package com.tech.oauth2.client.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/10
 */
@RestController
@RequestMapping("/api/")
public class TestController {

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
