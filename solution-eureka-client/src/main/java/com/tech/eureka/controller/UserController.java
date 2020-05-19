package com.tech.eureka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/19
 */
@RestController
@Slf4j
@RequestMapping("api/user/")
public class UserController {

    @GetMapping(value = "/getId")
    public Integer getUserId(@RequestParam("id") Integer id){
        return 12345;
    }
}
