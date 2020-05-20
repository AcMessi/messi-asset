package com.tech.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Classname AsyncConfig
 * @Description 异步线程调度配置
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Value("${async.executor.core.pool.size}")
    public int corePoolSize;

    @Value("${async.executor.max.pool.size}")
    public int maxPoolSize;

    @Value("${async.executor.queue.capacity}")
    public int capacity;

    @Value("${async.executor.thread.name.prefix}")
    public String namePrefix;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(capacity);
        executor.setThreadNamePrefix(namePrefix);
        executor.initialize(); //如果不初始化，导致找到不到执行器
        return executor;
    }

}
