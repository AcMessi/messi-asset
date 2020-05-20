package com.tech.quartz.service;

import org.quartz.Scheduler;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 定时任务服务接口
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
public interface QuartzJobService {

    /**
     * 添加任务
     *
     * @param scheduler      Scheduler的实例
     * @param jobClassName   任务类名称
     * @param jobGroupName   任务群组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
    void addJob(Scheduler scheduler, String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * 暂停任务
     *
     * @param scheduler    Scheduler的实例
     * @param jobClassName 任务类名称
     * @param jobGroupName 任务群组名称
     * @throws Exception
     */
    void pauseJob(Scheduler scheduler, String jobClassName, String jobGroupName) throws Exception;

    /**
     * 继续任务
     *
     * @param scheduler    Scheduler的实例
     * @param jobClassName 任务类名称
     * @param jobGroupName 任务群组名称
     * @throws Exception
     */
    void resumeJob(Scheduler scheduler, String jobClassName, String jobGroupName) throws Exception;

    /**
     * 重新执行任务
     *
     * @param scheduler      Scheduler的实例
     * @param jobClassName   任务类名称
     * @param jobGroupName   任务群组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
    void rescheduleJob(Scheduler scheduler, String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * 删除任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    void deleteJob(Scheduler scheduler, String jobClassName, String jobGroupName) throws Exception;

    /**
     * 刷新token
     */
    public void refreshToken(CountDownLatch latch);
}
