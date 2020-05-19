package com.tech.hystrix.service;

import com.tech.hystrix.service.impl.UserFeginFailBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 表示"user-service"的服务，指定fallback
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/19
 */
@FeignClient(value = "eureka-client-01", fallback = UserFeginFailBackImpl.class)
public interface UserFeginService {

    @GetMapping(value = "/api/user/getId")
    public Integer getUserId(@RequestParam("id") Integer id);
}
