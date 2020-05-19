package com.tech.hystrix.service.impl;

import com.tech.hystrix.service.UserFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/19
 */
@Slf4j
@Component
public class UserFeginFailBackImpl implements UserFeginService {

    @Override
    public Integer getUserId(Integer id) {
        log.info("熔断，默认回调函数");
        return 0000;
    }
}
