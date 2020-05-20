package com.tech.quartz.job;

import com.tech.quartz.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Description accesstoken定时任务类
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Component
@Slf4j
public class TokenJob implements BaseJob {

    @Autowired
    QuartzJobService quartzJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("定时任务TokenJob开始");

        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            quartzJobService.refreshToken(latch);
        }

        try {
            // 等待所有线程任务都跑完
            latch.await();
        } catch (InterruptedException e) {
            log.info("error message : {}" + e.getMessage());
        }
        log.info("定时任务TokenJob结束");
    }


}