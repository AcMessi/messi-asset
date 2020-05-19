package com.tech.hystrix.controller;

import com.tech.hystrix.service.UserFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/19
 */
@RestController
@Slf4j
@RequestMapping("api/user/")
public class FeginHystrixController {

    @Autowired
    private UserFeginService userFeginService;

    @GetMapping("/getId")
    public Integer getUserId(Integer id) {
        Integer userId = userFeginService.getUserId(id);
        log.info("user id:" + userId);
        return userId;
    }
}
