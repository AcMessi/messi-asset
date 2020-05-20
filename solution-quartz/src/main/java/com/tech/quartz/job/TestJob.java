package com.tech.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @Description 定时任务测试类
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Slf4j
public class TestJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("test job开始执行时间: " + new Date());
    }
}