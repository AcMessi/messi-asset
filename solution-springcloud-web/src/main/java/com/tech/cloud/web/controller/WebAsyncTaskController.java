package com.tech.cloud.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/19
 */
@RestController
@Slf4j
@RequestMapping("api/web/")
public class WebAsyncTaskController {

    @Autowired
    @Qualifier("taskExecutor")
    ThreadPoolTaskExecutor executor;

    @GetMapping
    public WebAsyncTask<String> getNames() {
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(0L, executor, () -> {
            log.info("Thread Name {}", Thread.currentThread().getName());
            log.info("active count {}", executor.getActiveCount());
            log.info("thread count {}", executor.getPoolSize());
            Thread.sleep(2000);
            return "hello web async task";
        });

        asyncTask.onCompletion(() -> {
            log.info("{} task ending,", Thread.currentThread().getName());
        });

        asyncTask.onTimeout(() -> {
            log.info("{} task time out,", Thread.currentThread().getName());
            return "task time out";
        });

        asyncTask.onError(() -> {
            log.info("{} task exception,", Thread.currentThread().getName());
            return "task exception";
        });

        return asyncTask;
    }
}
