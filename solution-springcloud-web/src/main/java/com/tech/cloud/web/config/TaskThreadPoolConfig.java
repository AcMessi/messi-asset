package com.tech.cloud.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/19
 */
@Configuration
public class TaskThreadPoolConfig {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1); // 核心线程数，核心线程会一直存活，即使没有任务需要执行
        taskExecutor.setMaxPoolSize(2); // 最大线程数, 核心线程数已满，且队列已满，才会创建超过核心线程数的部分，但不能超过最大线程数
        taskExecutor.setQueueCapacity(1); // 任务队列容量
        taskExecutor.setThreadNamePrefix("asyncTask");
        return taskExecutor;
    }
}
