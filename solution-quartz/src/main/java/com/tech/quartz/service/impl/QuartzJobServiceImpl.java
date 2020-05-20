package com.tech.quartz.service.impl;

import com.tech.quartz.service.QuartzJobService;
import com.tech.quartz.util.QuartzJobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 定时任务服务实现类
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Service
@Slf4j
public class QuartzJobServiceImpl implements QuartzJobService {

    /**
     * 添加任务
     *
     * @param scheduler      Scheduler的实例
     * @param jobClassName   任务类名称
     * @param jobGroupName   任务群组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
    @Override
    public void addJob(Scheduler scheduler, String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobUtil.getClass(jobClassName).getClass())
                .withIdentity(jobClassName, jobGroupName)
                .build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobClassName, jobGroupName)
                .withSchedule(builder)
                .build();
        // 配置scheduler相关参数
        scheduler.scheduleJob(jobDetail, trigger);

        // 启动调度器
        scheduler.start();
    }

    /**
     * 暂停任务
     *
     * @param scheduler    Scheduler的实例
     * @param jobClassName 任务类名称
     * @param jobGroupName 任务群组名称
     * @throws Exception
     */
    @Override
    public void pauseJob(Scheduler scheduler, String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 继续任务
     *
     * @param scheduler    Scheduler的实例
     * @param jobClassName 任务类名称
     * @param jobGroupName 任务群组名称
     * @throws Exception
     */
    @Override
    public void resumeJob(Scheduler scheduler, String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 重新执行任务
     *
     * @param scheduler      Scheduler的实例
     * @param jobClassName   任务类名称
     * @param jobGroupName   任务群组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
    @Override
    public void rescheduleJob(Scheduler scheduler, String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder()
                .withIdentity(jobClassName, jobGroupName)
                .withSchedule(builder)
                .build();
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 删除任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @Override
    public void deleteJob(Scheduler scheduler, String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    @Async
    public void refreshToken(CountDownLatch latch) {
        // 业务代码
        log.info("refresh token");

        // decrements the count of the latch
        latch.countDown();
    }
}